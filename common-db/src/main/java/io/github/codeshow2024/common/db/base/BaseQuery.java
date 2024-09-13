package io.github.codeshow2024.common.db.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 分页查询基础类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Schema(description = "分页查询基础类")
public class BaseQuery {
    @Schema(description = "页数")
    private Integer pageNum;
    @Schema(description = "条数")
    private Integer pageSize;
}
