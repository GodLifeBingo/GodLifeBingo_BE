package f_thon.godlifebingo.core.godlife.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GodLifeStatistic {
    private String title;
    private Long id;
    private Double privateAchievementRate;
    private Double globalAchievementRate;

    public GodLifeStatistic(String title, Long id, Long privateProgressSum, Long privateTotalSum, Double globalAchievementRate) {
        this.title = title;
        this.id = id;
        if (privateTotalSum == null) {
            this.privateAchievementRate = 0D;
        } else {
            this.privateAchievementRate = privateProgressSum.doubleValue() / privateTotalSum.doubleValue();
        }

        this.globalAchievementRate = globalAchievementRate;
    }
}
