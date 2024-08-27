package top.codeshow.common.db.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 将前端null值让long可以接收
 */
@Component
public class StringToIntegerConverter implements Converter<String, Integer> {

    @Override
    public Integer convert(String source) {
        try {
            if (source.equals("null")) {
                return null;
            } else
                return Integer.parseInt(source);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Integer value: " + source, e);
        }
    }
}

