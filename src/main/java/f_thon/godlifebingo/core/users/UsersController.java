package f_thon.godlifebingo.core.users;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
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
}
