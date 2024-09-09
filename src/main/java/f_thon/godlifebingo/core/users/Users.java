package f_thon.godlifebingo.core.users;

import f_thon.godlifebingo.core.users.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
}