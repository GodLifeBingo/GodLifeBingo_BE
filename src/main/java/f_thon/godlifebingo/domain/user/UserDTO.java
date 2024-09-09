package f_thon.godlifebingo.domain.user;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;


@Builder
@Getter
public class UserDTO {
    private String providerType;
    private UserRole role;
    private String name;
    private String username;
}
