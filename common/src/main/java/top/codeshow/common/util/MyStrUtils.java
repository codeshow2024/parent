package top.codeshow.common.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * String工具类
 */
@Slf4j
public class MyStrUtils {
    /**
     * 加密密码
     *
     * @param oldPassword 原密码
     * @return 加密过的密码
     */
    public static String encryptPassword(String oldPassword) {
        return SecureUtil.md5(oldPassword);
    }


    /**
     * 生成 uuid
     *
     * @return
     */
    public static String getUuid() {
        return IdUtil.fastSimpleUUID();
    }
}
