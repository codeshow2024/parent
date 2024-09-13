package io.github.codeshow2024.common.db.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 将前端null值让long可以接收
 */
@Component
public class StringToLongConverter implements Converter<String, Long> {

    @Override
    public Long convert(String source) {
        try {
            if (source.equals("null")) {
                return null;
            } else
                return Long.parseLong(source);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Long value: " + source, e);
        }
    }
}

