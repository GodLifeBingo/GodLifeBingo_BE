package f_thon.godlifebingo.core.CellHistory;

import f_thon.godlifebingo.core.cell.Cell;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

public interface CellHistoryRepository extends JpaRepository<CellHistory, Long> {

    @Query("""
          select 
            case
              when count(ch) > 0 then true
              else false           
            end
          from 
            CellHistory ch 
          where 
            ch.cell = :cell 
            and DATE(ch.createdAt) = CURDATE()
        """)
    boolean existsByCellAndCreatedAt(@Param("cell") Cell cell);

    @Query("""
      select
        ch
      from 
        CellHistory ch
      where
        ch.cell = :cell
       and DATE(ch.createdAt) = CURDATE()
    """)
    Optional<CellHistory> findByCellAndCreatedAt(@Param("cell") Cell cell);
}
