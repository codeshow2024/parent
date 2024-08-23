package top.codeshow.rabbitmq.config;

import cn.hutool.core.map.MapUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq 交换机配置
 */
@Getter
@Slf4j
@Configuration
public class MqExchangeConfig {
    // 统一前缀
    public static final String CODESHOW_MQ_PRE = "codeshow.mq";
    // 即时交换机名称
    public static final String CODESHOW_MQ_EXCHANGE = CODESHOW_MQ_PRE + ".exchange";
    // 延迟交换机名称
    public static final String CODESHOW_MQ_DELAYED_EXCHANGE = CODESHOW_MQ_PRE + ".delayed.exchange";

    /**
     * 即时交换机
     */
    @Bean
    DirectExchange directExchange() {
        return ExchangeBuilder
                .directExchange(CODESHOW_MQ_EXCHANGE)
                .durable(true)
                .build();
    }

    /**
     * 延迟交换机
     */
    @Bean
    public CustomExchange delayDirectExchange() {
        return new CustomExchange(
                CODESHOW_MQ_DELAYED_EXCHANGE,
                "x-delayed-message",
                true,
                false,
                MapUtil.of("x-delayed-type", "direct")
        );
    }
}
