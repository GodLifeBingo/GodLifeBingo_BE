package f_thon.godlifebingo.core.cell;

import f_thon.godlifebingo.core.bingo.Bingo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CellRepository extends JpaRepository<Cell, Long> {

    @EntityGraph(attributePaths = {"bingo", "godlife"})
    Optional<Cell> findById(Long cellId);

    @EntityGraph(attributePaths = "godlife")
    List<Cell> findAllByBingo(Bingo bingo);

    @Query("select c from Cell c where c.bingo.id = :bingoId")
    List<Cell> findByBingoId(@Param("bingoId") Long bingoId);
}
