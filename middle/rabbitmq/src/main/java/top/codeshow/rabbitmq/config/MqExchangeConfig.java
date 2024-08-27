package top.codeshow.rabbitmq.config;

import cn.hutool.core.map.MapUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq 交换机配置
 */
@Getter
@Slf4j
@Configuration
public class MqExchangeConfig {
    // 即时交换机名称
    public static String MQ_EXCHANGE;
    // 延迟交换机名称
    public static String MQ_DELAYED_EXCHANGE;


    public MqExchangeConfig(@Value("${codeshow.rabbitmq.exchange}") String mqExchange, @Value("${codeshow.rabbitmq.delayed_exchange}") String delayedExchange) {
        MQ_EXCHANGE = mqExchange;
        MQ_DELAYED_EXCHANGE = delayedExchange;
    }

    /**
     * 即时交换机
     */
    @Bean
    DirectExchange directExchange() {
        return ExchangeBuilder
                .directExchange(MQ_EXCHANGE)
                .durable(true)
                .build();
    }

    /**
     * 延迟交换机
     */
    @Bean
    public CustomExchange delayDirectExchange() {
        return new CustomExchange(
                MQ_DELAYED_EXCHANGE,
                "x-delayed-message",
                true,
                false,
                MapUtil.of("x-delayed-type", "direct")
        );
    }
}
