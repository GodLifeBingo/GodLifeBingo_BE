package f_thon.godlifebingo.core.godlife;

import f_thon.godlifebingo.core.godlife.SimpleGodLifeListResponse.SimpleGodLifeResponse;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GodLifeRepository extends JpaRepository<GodLife, Long> {
}
