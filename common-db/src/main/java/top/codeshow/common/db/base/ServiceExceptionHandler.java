package top.codeshow.common.db.base;


import cn.hutool.core.util.StrUtil;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.codeshow.common.db.en.CodeEnum;

import java.util.Set;

/**
 * 异常统一处理
 */
@Slf4j
@RestControllerAdvice
public class ServiceExceptionHandler {

    /**
     * 业务异常
     */
    @ExceptionHandler(ServiceException.class)
    public Result<Void> handle(ServiceException e) {
        e.printStackTrace();
        log.error("自定义异常:状态码{},返回信息{}", e.getStatus(), e.getMessage());
        return Result.error(e.getStatus(), e.getMessage());
    }

    /**
     * 参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> paramException(MethodArgumentNotValidException e) {
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        // 字段名称
        assert fieldError != null;
        String field = fieldError.getField();
        String defaultMessage = fieldError.getDefaultMessage();
        log.error("参数校验异常:{}字段{}", field, defaultMessage);
        return Result.error(CodeEnum.CHECK_ERROR.getCode(), defaultMessage);
    }


    /**
     * 参数校验异常补充
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<Void> paramException(ConstraintViolationException e) {
        e.printStackTrace();
        StringBuilder sb = new StringBuilder();
        // 添加异常中所有的校验错误信息(一般set的size为1,只有一条异常信息)
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            // 获取自定义异常信息
            String message = constraintViolation.getMessage();
            log.info(message);
            sb.append(message);
        }
        // 如果没有自定义异常，则使用默认异常信息
        if (StrUtil.isBlank(sb.toString())) {
            sb.append(e.getMessage());
        }
        log.error("参数校验异常:{}", sb);
        return Result.error(CodeEnum.CHECK_ERROR.getCode(), sb.toString());
    }

    /**
     * 没有捕获到的运行异常处理
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> otherException(Exception e) {
        e.printStackTrace();
        log.error("操作失败", e);
        return Result.error(CodeEnum.MYSQL_ERROR.getCode(), CodeEnum.MYSQL_ERROR.getMessage());
    }
}

