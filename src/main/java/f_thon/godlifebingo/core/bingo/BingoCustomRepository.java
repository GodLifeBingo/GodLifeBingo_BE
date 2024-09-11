package f_thon.godlifebingo.core.bingo;

import java.util.List;

public interface BingoCustomRepository {
    List<Bingo> getList(Long userId, long limit, long offset);
}
