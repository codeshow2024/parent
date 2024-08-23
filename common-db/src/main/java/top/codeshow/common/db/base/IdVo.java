package top.codeshow.common.db.base;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 通过RequestBody方式接收 id 参数
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdVo {
    @Schema(description = "id")
    private Long id;
    @Schema(description = "id列表")
    private List<Long> ids;
}
