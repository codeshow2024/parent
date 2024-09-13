package io.github.codeshow2024.common.db.en;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
@AllArgsConstructor
public enum StatusEnum {

    ABLE(0, "启用"),
    DISABLE(1, "禁用");
    private final Integer code;
    private final String message;

    /**
     * 根据code寻找枚举类
     *
     * @param code code码
     * @return 枚举类
     */
    public StatusEnum getByCode(Integer code) {
        return Arrays.stream(StatusEnum.values())
                .filter(en -> Objects.equals(en.code, code))
                .findAny().orElse(null);
    }
}
