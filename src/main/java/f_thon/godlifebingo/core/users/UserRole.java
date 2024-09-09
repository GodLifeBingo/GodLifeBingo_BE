package f_thon.godlifebingo.core.users;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserRole {
    ADMIN("ROLE_ADMIN", "admin"),
    USER("ROLE_USER", "user"),
    GUEST("ROLE_GUEST", "guest");

    private String role;
    private String title;
}