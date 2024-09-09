package f_thon.godlifebingo.core.cell;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CellRepository extends JpaRepository<Cell, Long> {

    @Query("select c"
        + " from Cell c"
        + " where c.bingo.id in (select b.id"
        + " from Users u join Bingo b on u.id = b.users.id"
        + " where u.id = :userId and b.isRepresentative = true)")
    List<Cell> findByRepresentativeBingoId(@Param("userId") Long userId);
}
