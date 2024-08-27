package top.codeshow.common.db.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import top.codeshow.common.db.anno.RedisCache;
import top.codeshow.common.db.constant.RedisConstant;
import top.codeshow.redis.RedisUtils;

import java.lang.reflect.Method;

/**
 * redisCache 切面
 */
@Slf4j
@Aspect
@Component
public class RedisCacheAspect {
    @Pointcut("@annotation(top.codeshow.common.db.anno.RedisCache)")
    public void setJoinPoint() {
    }

    @Around(value = "setJoinPoint()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getName();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        String key = String.join(":", RedisConstant.REDIS_PREFIX, className, methodName);
        Object[] argValues = joinPoint.getArgs();
        for (Object param : argValues) {
            key = String.join(":", key, param.toString());
        }
        Object result = RedisUtils.get(key);
        if (result != null) {
            log.info("redisCache从redis中获取 key=[{}],value=[{}]", key, result);
        } else {
            result = joinPoint.proceed();
            if (method.isAnnotationPresent(RedisCache.class)) {
                RedisCache redisCache = method.getAnnotation(RedisCache.class);
                RedisUtils.set(key, result, redisCache.time(), redisCache.timeunit());
                log.info("redisCache缓存值到redis中,key=[{}],value=[{}],过期时间为[{},{}]", key, result, redisCache.time(), redisCache.timeunit());
            }
        }
        return result;
    }
}