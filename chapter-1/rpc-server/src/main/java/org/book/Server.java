package org.book;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务类
 */
public class Server {

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    private static final HashMap<String, Class> serviceRegistry = new HashMap<String, Class>();

    /**
     * 注册服务
     *
     * @param serviceInterface 服务接口
     * @param impl             服务实现类
     */
    public void register(Class serviceInterface, Class impl) {
        //注册服务
        serviceRegistry.put(serviceInterface.getName(), impl);
    }

    /**
     * 启动服务
     *
     * @param port 启动端口
     * @throws IOException
     */
    public void start(int port) throws IOException {
        final ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress(port));
        System.out.println("服务已启动");
        while (true) {
            executor.execute(new Runnable() {
                public void run() {
                    Socket socket = null;
                    ObjectInputStream input = null;
                    ObjectOutputStream output = null;
                    try {
                        socket = server.accept();
                        // 接收到服务调用请求，将码流反序列化定位具体服务
                        input = new ObjectInputStream(socket.getInputStream());
                        String serviceName = input.readUTF();
                        String methodName = input.readUTF();
                        Class<?>[] parameterTypes = (Class<?>[]) input.readObject();
                        Object[] arguments = (Object[]) input.readObject();
                        // 在服务注册表中根据调用的服务获取到具体的实现类
                        Class serviceClass = serviceRegistry.get(serviceName);
                        if (serviceClass == null) {
                            throw new ClassNotFoundException(serviceName + " 未找到");
                        }
                        Method method = serviceClass.getMethod(methodName, parameterTypes);
                        // 调用获取结果
                        Object result = method.invoke(serviceClass.newInstance(), arguments);
                        // 将结果序列化后发送回客户端
                        output = new ObjectOutputStream(socket.getOutputStream());
                        output.writeObject(result);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        // 关闭资源
                        try {
                            if (socket != null) socket.close();
                            if (input == null) input.close();
                            if (output == null) output.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}
