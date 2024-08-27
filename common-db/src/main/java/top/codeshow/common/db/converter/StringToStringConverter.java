package top.codeshow.common.db.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 将前端null值让long可以接收
 */
@Component
public class StringToStringConverter implements Converter<String, String> {

    @Override
    public String convert(String source) {
        try {
            if (source.equals("null")) {
                return null;
            } else
                return source;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid String value: " + source, e);
        }
    }
}

