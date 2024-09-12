package f_thon.godlifebingo.core.users;

import java.util.Optional;
import javax.swing.text.html.Option;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public void deleteUser(Long userId) {
        Optional<Users> optionalUser = usersRepository.findById(userId);

        optionalUser.ifPresentOrElse(user -> {
            user.deleteUser();
        }, () -> {
            log.error("유저를 찾을 수 없습니다. user Id : {}", userId);
            throw new RuntimeException();
        });
    }
}
