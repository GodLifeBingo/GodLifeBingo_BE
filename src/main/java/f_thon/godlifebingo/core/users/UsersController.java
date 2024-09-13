package f_thon.godlifebingo.core.users;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import f_thon.godlifebingo.common.security.dto.CustomOAuth2User;
import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @DeleteMapping
    public void deleteUser(@CurrentUserId Long userId, HttpSession session) {
        usersService.deleteUser(userId);
        session.invalidate();
    }

    @GetMapping("/session_check")
    public Map<String, Boolean> isLogin() {
        Map<String, Boolean> result = new HashMap<>();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof CustomOAuth2User oAuth2User) {
            result.put("hasSession", true);
        } else {
            result.put("hasSession", false);
        }

        return result;
    }
}
