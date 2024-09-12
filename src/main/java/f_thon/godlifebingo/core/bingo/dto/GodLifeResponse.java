package f_thon.godlifebingo.core.bingo.dto;

import f_thon.godlifebingo.core.godlife.GodLife;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GodLifeResponse {
    private Long godLifeId;
    private String title;
    private String description;
    private String imageUrl;
    private BigDecimal allUserCompletedRate;
    private Boolean isOneOff;

    public static GodLifeResponse of(GodLife godLife){
        return GodLifeResponse.builder()
            .godLifeId(godLife.getId())
            .title(godLife.getTitle())
            .description(godLife.getDescription())
            .imageUrl(godLife.getImageUrl())
            .allUserCompletedRate(BigDecimal.valueOf(godLife.getAllUserCompletedRate()))
            .isOneOff(godLife.isOneOff())
            .build();
    }
}
