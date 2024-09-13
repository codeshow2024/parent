package io.github.codeshow2024.common.db.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import io.github.codeshow2024.common.en.CodeEnum;

import java.time.LocalDateTime;

/**
 * 返回结果
 */
@Data
@NoArgsConstructor
public class Result<T> {
    @Schema(description = "当前时间")
    private final LocalDateTime currentTime = LocalDateTime.now();
    @Schema(description = "数据")
    private T data;
    @Schema(description = "信息")
    private String message;
    @Schema(description = "状态码")
    private int code;
    @Schema(description = "是否成功")
    private boolean flag;

    public Result(T data, String message, int code, boolean flag) {
        this.data = data;
        this.message = message;
        this.code = code;
        this.flag = flag;
    }

    public static <T> Result<T> data(T data) {
        return new Result<>(data, CodeEnum.SUCCESS.getMessage(), CodeEnum.SUCCESS.getCode(), true);
    }

    public static <T> Result<T> success() {
        return data(null);
    }

    public static <T> Result<T> message(String message) {
        return new Result<>(null, message, CodeEnum.SUCCESS.getCode(), true);
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(null, message, code, false);
    }
}
