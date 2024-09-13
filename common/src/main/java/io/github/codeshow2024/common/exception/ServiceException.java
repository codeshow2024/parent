package io.github.codeshow2024.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import io.github.codeshow2024.common.en.CodeEnum;

/**
 * 自定义异常类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    private final int status;
    private final String message;

    public ServiceException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
        this.message = message;
    }

    public ServiceException(Integer status, String message) {
        super(message);
        this.status = status;
        this.message = message;
    }

    public ServiceException() {
        this.status = CodeEnum.ERROR.getCode();
        this.message = CodeEnum.ERROR.getMessage();
    }

    public ServiceException(CodeEnum statusCodeEum) {
        super(statusCodeEum.toString());
        this.status = statusCodeEum.getCode();
        this.message = statusCodeEum.getMessage();
    }

    public ServiceException(String message) {
        this.status = CodeEnum.ERROR.getCode();
        this.message = message;
    }
}
