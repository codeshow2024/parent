package top.codeshow.common.db.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import top.codeshow.common.db.en.CodeEnum;

@Data
@EqualsAndHashCode(callSuper = true)
@Schema(description = "自定义异常类")
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
