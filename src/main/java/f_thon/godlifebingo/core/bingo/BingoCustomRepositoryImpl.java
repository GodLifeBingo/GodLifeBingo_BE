package f_thon.godlifebingo.core.bingo;

import static f_thon.godlifebingo.core.bingo.QBingo.*;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import f_thon.godlifebingo.core.bingo.QBingo;
import f_thon.godlifebingo.core.bingo.dto.BingoListRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoListResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoRow;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BingoCustomRepositoryImpl implements BingoCustomRepository{

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public BingoListResponse getList(Long userId, BingoListRequest request) {
        List<Bingo> bingoList = jpaQueryFactory.selectFrom(bingo)
            .where(bingo.users.id.eq(userId),
                titleLike(request.getTitle()),
                startDateGoe(request.getStartDate()),
                endDateLoe(request.getEndDate())
            )
            .limit(request.getLimit() == null ? 10 : request.getLimit())
            .offset((request.getOffset() == null ? 0 : request.getOffset() - 1)
                * (request.getLimit() == null ? 10 : request.getLimit()))
            .fetch();

        Long count = jpaQueryFactory.select(bingo.count())
            .from(bingo)
            .where(bingo.users.id.eq(userId),
                titleLike(request.getTitle()),
                startDateGoe(request.getStartDate()),
                endDateLoe(request.getEndDate())
            ).fetchFirst();

        count = count / (request.getLimit()==null ? 10 : request.getLimit()) + 1;

        List<BingoRow> bingoRowList = bingoList.stream()
            .map(BingoRow::of)
            .toList();

        return BingoListResponse.builder()
            .bingoList(bingoRowList)
            .allPage(count)
            .build();
    }

    private static BooleanExpression endDateLoe(LocalDateTime endDate) {
        return endDate==null ? null : bingo.endDate.loe(endDate);
    }

    private static BooleanExpression startDateGoe(LocalDateTime startDate) {
        return startDate==null ? null : bingo.startDate.goe(startDate);
    }

    private static BooleanExpression titleLike(String title) {
        return title==null ? null : bingo.title.contains(title);
    }
}
