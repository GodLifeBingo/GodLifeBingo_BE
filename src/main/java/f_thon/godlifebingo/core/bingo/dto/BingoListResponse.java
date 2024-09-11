package f_thon.godlifebingo.core.bingo.dto;

import f_thon.godlifebingo.core.bingo.Bingo;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BingoListResponse {
    private List<BingoRow> bingoList;
    private Long allPage;
}
