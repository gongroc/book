package org.book.rpc.dubbo.client.service.imple;

import com.alibaba.dubbo.config.annotation.Reference;
import org.book.rpc.dubbo.service.IHello;
import org.springframework.stereotype.Component;

@Component
public class InvokeService {

    @Reference
    public IHello hello;


}
