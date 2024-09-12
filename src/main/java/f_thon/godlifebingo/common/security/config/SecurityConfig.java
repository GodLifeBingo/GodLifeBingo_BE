package f_thon.godlifebingo.common.security.config;

import f_thon.godlifebingo.common.security.handler.CustomOAuth2SuccessHandler;
import f_thon.godlifebingo.common.security.service.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CustomOAuth2UserService customOAuth2UserService;

//    @Bean
//    public CookieSerializer cookieSerializer() {
//        DefaultCookieSerializer serializer = new DefaultCookieSerializer();
//        serializer.setSameSite("None");
//        serializer.setUseSecureCookie(true);
//        return serializer;
//    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .oauth2Login(oauth2 -> oauth2
                .userInfoEndpoint(userInfoEndpointConfig -> userInfoEndpointConfig
                    .userService(customOAuth2UserService))
                .loginPage("/login.html")
                .successHandler(customOAuth2SuccessHandler));

        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/oauth2/**", "/login", "/api/godlifes", "/login.html").permitAll()
                .anyRequest().authenticated());

        http
            .logout(logout -> logout
                .logoutSuccessUrl("/"));

        return http.build();
    }
}
