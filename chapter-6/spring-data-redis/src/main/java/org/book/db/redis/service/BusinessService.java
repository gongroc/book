package org.book.db.redis.service;

import org.book.db.redis.utils.DistributedLockHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class BusinessService {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public void redis() {

        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        //存入redis
        operations.set("key", "value");
        //存入redis并设置超时时间
        operations.set("key2", "value", 100, TimeUnit.MINUTES);
        //根据key获取value
        Object key = operations.get("key");
        // 根据key设置超时时间
        redisTemplate.expire("key", 10, TimeUnit.SECONDS);
        // 删除key
        redisTemplate.delete("key2");
        // 查询所有key
        Set<String> keys = redisTemplate.keys("*");

    }


    @Autowired
    private DistributedLockHandler distributedLockHandler;

    public void lockTest() {
        try {
            // 初次尝试并未锁住，将获得到锁
            System.out.println(distributedLockHandler.getLock("key", "value") ? "获得到锁" : "被锁住");
            // 再次尝试发现已经锁住
            System.out.println(distributedLockHandler.getLock("key", "value") ? "获得到锁" : "被锁住");
            // 模拟锁超时
            Thread.sleep(DistributedLockHandler.LOCK_EXPIRE);
            // 再次请求锁已超时，将再次获得到锁
            System.out.println(distributedLockHandler.getLock("key", "value") ? "获得到锁" : "被锁住");
            // 手动释放锁
            distributedLockHandler.releaseLock("key");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
