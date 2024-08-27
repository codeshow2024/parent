package top.codeshow.common.db.util;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import top.codeshow.common.db.base.BaseQuery;
import top.codeshow.common.db.base.Page;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtils {

    public static void setAll(BaseQuery bq) {
        bq.setPageNum(1);
        bq.setPageSize(Integer.MAX_VALUE);
    }

    /**
     * 开始分页
     */
    public static void init(BaseQuery bq) {
        if (bq.getPageNum() == null) {
            bq.setPageNum(1);
        }
        if (bq.getPageSize() == null) {
            bq.setPageSize(10);
        }
        PageHelper.startPage(bq.getPageNum(), bq.getPageSize());
    }

    /**
     * 将 pageInfo 转化为 Page
     *
     * @param pageInfo 分页的类型
     * @return page 自己定义的 page
     */
    public static <T> Page<T> fromPageInfoToPage(PageInfo<T> pageInfo) {
        Page<T> page = new Page<>();
        page.setPageNum(pageInfo.getPageNum())
                .setPageSize(pageInfo.getPageSize());
        page.setTotal((int) pageInfo.getTotal())
                .setTotalPage(pageInfo.getPages())
                .setList(pageInfo.getList());
        return page;
    }

    public static <T> Page<T> getPage(List<T> list) {
        PageInfo<T> pageInfo = new PageInfo<>(list);
        return fromPageInfoToPage(pageInfo);
    }

    public static <T, V> Page<V> getPage(List<T> list, Class<V> vClass) {
        Page<T> tPage = getPage(list);
        return tPage.copy(vClass);
    }
}
