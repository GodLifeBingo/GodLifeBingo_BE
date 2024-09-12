package f_thon.godlifebingo.core.users;

import f_thon.godlifebingo.core.users.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id @GeneratedValue
    private Long id;

    private String providerType;
    private String username;
    private String name;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    public void deleteUser() {
        // 유저 이름으로 로그인을 하니, 유저 이름을 변경해 소프트 삭제. 기록 유지용
        UUID uuid = UUID.randomUUID();
        username = "deleted_user_" + uuid.toString();
    }
}