package f_thon.godlifebingo.common.security.service;

import f_thon.godlifebingo.core.users.UserRole;
import f_thon.godlifebingo.core.users.Users;
import f_thon.godlifebingo.core.users.UsersRepository;
import f_thon.godlifebingo.common.security.dto.CustomOAuth2User;
import f_thon.godlifebingo.common.security.dto.GoogleOAuth2Response;
import f_thon.godlifebingo.common.security.dto.OAuth2Response;
import f_thon.godlifebingo.core.users.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UsersRepository usersRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        OAuth2Response oAuth2Response = null;

        if (registrationId.equals("google")) {
            oAuth2Response = new GoogleOAuth2Response(oAuth2User.getAttributes());
        } else {
            return null;
        }

        String name = oAuth2Response.getName();
        String email = oAuth2Response.getEmail();
        String provider = oAuth2Response.getProvider();
        String providerId = oAuth2Response.getProviderId();
        String username = provider + " " + providerId;


        Users existsUser = usersRepository.findByUsername(username).orElse(null);
//        User existsUser = userRepository.findByEmail(oAuth2Response.getEmail()).orElse(null);

        if (existsUser == null) {
            Users user = Users.builder()
                .providerType(provider)
                .username(username)
                .email(email)
                .name(name)
                .role(UserRole.USER)
                .build();

            usersRepository.save(user);

            UserDTO userDTO = UserDTO.builder()
                .id(user.getId())
                .username(username)
                .providerType(provider)
                .name(name)
                .role(UserRole.USER)
                .build();

            return new CustomOAuth2User(userDTO);
        } else {
            UserDTO userDTO = UserDTO.builder()
                .id(existsUser.getId())
                .providerType(existsUser.getProviderType())
                .username(existsUser.getUsername())
                .name(existsUser.getName())
                .role(existsUser.getRole())
                .build();

            return new CustomOAuth2User(userDTO);
        }
    }
}
