package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.common.entity.BaseEntity;
import f_thon.godlifebingo.core.users.Users;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Bingo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private int size;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String color;
    private int totalCount;
    private BigDecimal totalCompletedRate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users users;
}
