package top.codeshow.common.db.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载解析配置文件
 */
@Slf4j
@Configuration
@PropertySource("classpath:common-db-config.properties")
public class CommonDbConfig {
    static {
        log.info("扫描到这个文件了");
    }
}
