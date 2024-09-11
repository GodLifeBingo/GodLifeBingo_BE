package f_thon.godlifebingo.core.godlife;

import f_thon.godlifebingo.core.godlife.dto.GodLifeStatistic;
import f_thon.godlifebingo.core.users.Users;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.parameters.P;

public interface GodLifeRepository extends JpaRepository<GodLife, Long> {

    @Query("""
         select
           new f_thon.godlifebingo.core.godlife.GodLifeStatistic(gl.title, gl.id, sum(c.currentProgress), sum(b.totalCount), gl.allUserCompletedRate)
         from
           GodLife  gl
         left join Cell c on c.godlife = gl
         left join Bingo b on c.bingo = b and b.users = :user
         where
           gl.title like %:query%          
         group by 
           gl.id     
        """)
    Page<GodLifeStatistic> getGodLifeStatistics(@P("query") String query, @P("user")Users user, Pageable pageable);
}
