package f_thon.godlifebingo.core.bingo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import f_thon.godlifebingo.core.cell.Cell;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BingoCreateRequest {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS", timezone = "Asia/Seoul")
    private LocalDateTime endDate;
    private int size;
    private String color;
    private String title;
    private List<CellRequest> cells;


}
