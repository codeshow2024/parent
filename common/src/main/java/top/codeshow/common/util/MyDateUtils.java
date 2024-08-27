package top.codeshow.common.util;

import lombok.extern.slf4j.Slf4j;
import top.codeshow.common.exception.ServiceException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * 自定义时间类
 */
@Slf4j
public class MyDateUtils {
    /**
     * 获取到两个日期之间的所有日期
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static List<LocalDate> getBetweenDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new ServiceException("开始时间不应该晚于结束时间");
        }
        List<LocalDate> list = new ArrayList<>();
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            list.add(date);
            date = date.plusDays(1);
        }
        return list;
    }
}
