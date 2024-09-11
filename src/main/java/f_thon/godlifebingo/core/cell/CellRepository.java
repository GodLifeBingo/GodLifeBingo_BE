package f_thon.godlifebingo.core.cell;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CellRepository extends JpaRepository<Cell, Long> {

}
