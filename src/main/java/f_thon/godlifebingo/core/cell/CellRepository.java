package f_thon.godlifebingo.core.cell;

import f_thon.godlifebingo.core.bingo.Bingo;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository<Cell, Long> {

    @EntityGraph(attributePaths = "bingo")
    Optional<Cell> findById(Long cellId);

    @EntityGraph(attributePaths = "godlife")
    List<Cell> findAllByBingo(Bingo bingo);
}
