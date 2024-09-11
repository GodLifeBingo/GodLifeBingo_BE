package f_thon.godlifebingo.core.bingo;

import static f_thon.godlifebingo.core.bingo.QBingo.*;

import com.querydsl.jpa.impl.JPAQueryFactory;
import f_thon.godlifebingo.core.bingo.QBingo;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BingoCustomRepositoryImpl implements BingoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Bingo> getList(Long userId, long limit, long offset) {
        return jpaQueryFactory.selectFrom(bingo)
            .where(bingo.users.id.eq(userId))
            .limit(limit)
            .offset( (offset-1) * limit )
            .fetch();
    }
}
