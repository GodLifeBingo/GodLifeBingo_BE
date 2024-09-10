package f_thon.godlifebingo.core.bingo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import f_thon.godlifebingo.core.bingo.Bingo;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
    private int size;
    private String color;
    private String title;
    private int totalCount;

    public static BingoResponse of(Bingo bingo){
        return BingoResponse.builder()
            .startDate(bingo.getStartDate())
            .endDate(bingo.getEndDate())
            .size(bingo.getSize())
            .color(bingo.getColor())
            .title(bingo.getTitle())
            .totalCount(bingo.getTotalCount())
            .build();
    }
}
