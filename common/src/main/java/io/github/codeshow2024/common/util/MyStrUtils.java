package io.github.codeshow2024.common.util;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * String工具类
 */
@Slf4j
public class MyStrUtils {
    /**
     * 是否为空字符串
     *
     * @param str 字符串
     * @return
     */
    public static boolean isBlank(String str) {
        return null == str || str.trim().isEmpty();
    }

    /**
     * 不为空字符串
     *
     * @param str 字符串
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }

    /**
     * 删除字符串的空格
     *
     * @param str 字符串
     * @return
     */
    public static String removeBlank(String str) {
        if (str == null) {
            return null;
        }
        return str.replaceAll(" ", "");
    }

    /**
     * 生成 uuid
     *
     * @return
     */
    public static String getUuid() {
        return IdUtil.fastSimpleUUID();
    }

    /**
     * 加密密码
     *
     * @param oldPassword 原密码
     * @return 加密过的密码
     */
    public static String encryptPassword(String oldPassword) {
        return SecureUtil.md5(oldPassword);
    }

}
