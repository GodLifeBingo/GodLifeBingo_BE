package f_thon.godlifebingo.core.bingo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BingoRepository extends JpaRepository<Bingo, Long>, BingoCustomRepository {

}
