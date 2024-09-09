package f_thon.godlifebingo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN", "admin"),
    USER("ROLE_USER", "user"),
    GUEST("ROLE_GUEST", "guest");

    private String role;
    private String title;
}
