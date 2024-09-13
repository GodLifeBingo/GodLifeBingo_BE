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
public class BingoResponse {

    private Long bingoId;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
    private int size;
    private String color;
    private String title;
    private int totalCount;
    private BigDecimal totalCompletedRate;
    private boolean isCompleted;

    public static BingoResponse of(Bingo bingo){
        return BingoResponse.builder()
            .bingoId(bingo.getId())
            .startDate(bingo.getStartDate())
            .endDate(bingo.getEndDate())
            .size(bingo.getSize())
            .color(bingo.getColor())
            .title(bingo.getTitle())
            .totalCount(bingo.getTotalCount())
            .totalCompletedRate(bingo.getTotalCompletedRate())
            .isCompleted(bingo.isCompleted())
            .build();
    }
}
