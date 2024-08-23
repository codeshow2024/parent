package top.codeshow.common.db.en;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CodeEnum {
    SUCCESS(10000, "成功"),
    ERROR(20000, "操作失败"),
    CHECK_ERROR(21001, "参数错误"),
    LOGIN_ERROR(21002, "账号或密码错误"),
    USED_ERROR(21003, "关联的已经使用了，请取消后重试"),
    LOGIN_CHECK(21004, "登录失效，正在跳转到登录页面"),
    USERNAME_ERROR(21005, "账号重复"),
    STOCK_NOT_ENOUGH_ERROR(21006, "库存不足"),
    MYSQL_ERROR(22001, "数据库错误"),
    ;
    private final int code;
    private final String message;
}
