package f_thon.godlifebingo.common.security.handler;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomOAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
        Authentication authentication) throws IOException {

        String sessionId = request.getSession().getId();
        System.out.println(sessionId);

        Cookie cookie = new Cookie("JSESSIONID", sessionId);
        cookie.setSecure(true);  // HTTPS 사용 시 필수
        cookie.setHttpOnly(true); // 클라이언트 스크립트로부터 쿠키 보호
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60); // 쿠키의 유효시간 설정
        cookie.setDomain("*.vercel.app");

        response.addCookie(cookie);
        response.sendRedirect("https://god-life-bingo.vercel.app");
    }
}
