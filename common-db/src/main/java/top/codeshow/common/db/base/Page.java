package top.codeshow.common.db.base;

import cn.hutool.core.bean.BeanUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 分页结果类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Schema(description = "分页结果类")
public class Page<T> extends BaseQuery {
    @Schema(description = "总条数")
    private Integer total;
    @Schema(description = "总页数")
    private Integer totalPage;
    @Schema(description = "列表")
    private List<T> list;

    public Page(Integer pageNum, Integer pageSize, Integer total, Integer totalPage, List<T> list) {
        super(pageNum, pageSize);
        this.total = total;
        this.totalPage = totalPage;
        this.list = list;
    }

    /**
     * 复制成 clazz 的 Page
     *
     * @param clazz 新的类型
     */
    public <V> Page<V> copy(Class<V> clazz) {
        return new Page<>(this.getPageNum(), this.getPageSize(), total, totalPage, BeanUtil.copyToList(list, clazz));
    }
}
