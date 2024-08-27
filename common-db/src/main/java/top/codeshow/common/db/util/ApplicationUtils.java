package top.codeshow.common.db.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import top.codeshow.common.exception.ServiceException;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Slf4j
public class ApplicationUtils {
    public static void run(Class<?> cls) {
        System.setProperty("spring.amqp.deserialization.trust.all", "true");
        ApplicationContext application = SpringApplication.run(cls);
        Environment env = application.getEnvironment();
        String host;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new ServiceException("启动 host 错误");
        }
        String port = env.getProperty("server.port");
        // 添加统一拦截地址
        String contextPath = env.getProperty("server.servlet.context-path");
        contextPath = contextPath == null ? "" : contextPath;
        log.info("""

                        ----------------------------------------------------------
                        应用程序 '{}' 正在启动!!! 访问地址为:
                        内网地址: http://{}:{}
                        Swagger文档地址: http://localhost:{}{}/doc.html
                        ----------------------------------------------------------""",
                env.getProperty("spring.application.name"),
                host,
                port,
                port,
                contextPath);
    }
}
