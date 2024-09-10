package f_thon.godlifebingo.core.cell;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CellRepository extends JpaRepository<Cell, Long> {

    @Query("select c from Cell c where c.bingo.id = :bingoId")
    List<Cell> findByBingoId(@Param("bingoId") Long bingoId);
}
