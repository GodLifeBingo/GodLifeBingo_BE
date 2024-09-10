package f_thon.godlifebingo.core.bingo.dto;

import f_thon.godlifebingo.core.bingo.Bingo;
import f_thon.godlifebingo.core.cell.Cell;
import f_thon.godlifebingo.core.godlife.GodLife;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CellRequest {
    private Long godLifeId;
    private int row;
    private int col;

    public Cell toCell(Bingo bingo){
        return Cell.builder()
            .r(row)
            .c(col)
            .bingo(bingo)
            .godlife(GodLife.builder().id(godLifeId).build())
            .build();
    }
}
