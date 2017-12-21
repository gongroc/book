package org.book.db.redis.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Component
public class DistributedLockHandler {

    private final static long LOCK_EXPIRE = 30 * 1000L;
    private final static long LOCK_TRY_INTERVAL = 30L;
    private final static long LOCK_TRY_TIMEOUT = 20 * 1000L;

    public boolean tryLock(Lock lock) {
        return getLock(lock, LOCK_TRY_TIMEOUT, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    public boolean tryLock(Lock lock, long timeout) {
        return getLock(lock, timeout, LOCK_TRY_INTERVAL, LOCK_EXPIRE);
    }

    public boolean tryLock(Lock lock, long timeout, long tryInterval) {
        return getLock(lock, timeout, tryInterval, LOCK_EXPIRE);
    }

    public boolean tryLock(Lock lock, long timeout, long tryInterval, long exprire) {
        return getLock(lock, timeout, tryInterval, exprire);
    }

    @Autowired
    private RedisTemplate redisTemplate;

    public boolean getLock(Lock lock, long timeout, long tryInterval, long lockExpireTime) {
        try {
            if (StringUtils.isEmpty(lock.getKey()) || StringUtils.isEmpty(lock.getValue())) {
                return false;
            }

            long startTime = System.currentTimeMillis();

            do {
                if (!redisTemplate.hasKey(lock.getKey())) {
                    redisTemplate.opsForValue().set(lock.getKey(), lock.getValue(), lockExpireTime, TimeUnit.MILLISECONDS);
                    return true;
                }
                if (System.currentTimeMillis() - startTime > timeout) {
                    return false;
                }
                Thread.sleep(tryInterval);
            } while (redisTemplate.hasKey(lock.getKey()));
        } catch (InterruptedException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public void releaseLock(Lock lock) {
        if (!StringUtils.isEmpty(lock.getKey())) {
            redisTemplate.delete(lock.getKey());
        }
    }
}
