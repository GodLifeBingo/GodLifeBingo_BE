package f_thon.godlifebingo.core.users;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class UserDTO {
    private Long id;
    private String providerType;
    private UserRole role;
    private String name;
    private String username;
}
