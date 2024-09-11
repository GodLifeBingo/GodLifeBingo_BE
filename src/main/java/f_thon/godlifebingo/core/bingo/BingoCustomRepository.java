package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.core.bingo.dto.BingoListRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoListResponse;
import java.util.List;

public interface BingoCustomRepository {
    BingoListResponse getList(Long userId, BingoListRequest request);
}
