package top.codeshow.redis;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * redis工具类
 */
@Component
public class RedisUtils {
    private static RedisTemplate redis;
    @Resource
    private RedisTemplate redisTemplate;

    public static <T> T get(String key) {
        Object o = redis.opsForValue().get(key);
        return o != null ? (T) o : null;
    }

    public static void set(String key, Object value) {
        redis.opsForValue().set(key, value);
    }

    public static void set(String key, Object value, long timeout, TimeUnit unit) {
        redis.opsForValue().set(key, value, timeout, unit);
    }

    public static boolean exist(String key) {
        return redis.hasKey(key);
    }

    public static boolean delete(String key) {
        return redis.delete(key);
    }

    @PostConstruct
    private void init() {
        RedisUtils.redis = this.redisTemplate;
    }
}
