package f_thon.godlifebingo.core.bingo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import f_thon.godlifebingo.core.bingo.Bingo;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BingoRow {
    private Long bingoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
    private String title;
    private BigDecimal totalCompletedRate;

    public static BingoRow of(Bingo bingo){
        return BingoRow.builder()
            .bingoId(bingo.getId())
            .startDate(bingo.getStartDate())
            .endDate(bingo.getEndDate())
            .title(bingo.getTitle())
            .totalCompletedRate(bingo.getTotalCompletedRate())
            .build();
    }
}
