package io.github.codeshow2024.common.util;

import org.springframework.web.multipart.MultipartFile;
import io.github.codeshow2024.common.exception.ServiceException;

import java.security.MessageDigest;

/**
 * 文件工具类
 */
public class MyFileUtils {
    /**
     * 计算文件的hash值
     *
     * @param file 文件
     */
    public static String calHashValue(MultipartFile file) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = file.getBytes();
            md.update(bytes);
            byte[] digest = md.digest();
            StringBuilder result = new StringBuilder();
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException("计算文件md5出错");
        }
    }
}
