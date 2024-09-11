package f_thon.godlifebingo.core.bingo.dto;

import f_thon.godlifebingo.core.cell.Cell;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellResponse {
    private GodLifeResponse godLife;
    private Long cellId;
    private int row;
    private int col;
    private int currentProgress;

    public static CellResponse of(Cell cell){
        return CellResponse.builder()
            .cellId(cell.getId())
            .row(cell.getRowNum())
            .col(cell.getColNum())
            .currentProgress(cell.getCurrentProgress())
            .godLife(GodLifeResponse.of(cell.getGodlife()))
            .build();
    }
}
