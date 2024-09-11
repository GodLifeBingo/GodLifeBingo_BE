package f_thon.godlifebingo.core.godlife.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GodLifeStatisticsListResponse {
    private List<GodLifeStatistic> godlifes;
    private Long totalCount;
    private Integer totalPage;
    private Integer page;
    private Integer size;
}
