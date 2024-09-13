package io.github.codeshow2024.common.db.anno;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * 自定义注解，结合AOP实现Redis自动缓存
 *
 * @author hulinhao
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
@Documented
public @interface RedisCache {
    long time() default 1;

    TimeUnit timeunit() default TimeUnit.HOURS;
}