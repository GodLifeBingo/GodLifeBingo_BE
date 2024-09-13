package f_thon.godlifebingo.core.bingo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BingoRepository extends JpaRepository<Bingo, Long>, BingoCustomRepository {

    /*@Modifying
    @Query("UPDATE bingo b, (SELECT b.id as bingo_id, SUM(c.current_progress / b.total_count) /  COUNT(b.id) * 100 as completed_rate"
        + " FROM bingo b"
        + " JOIN cell c ON b.id = c.bingo_id"
        + " GROUP BY b.id ) as tmp"
        + " SET b.total_completed_rate = tmp.completed_rate"
        + " where b.id = tmp.bingo_id")
    void updateTotalCompletedRate();*/
}
