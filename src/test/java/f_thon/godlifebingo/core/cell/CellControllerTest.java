package f_thon.godlifebingo.core.cell;

import static org.junit.jupiter.api.Assertions.*;

import f_thon.godlifebingo.core.bingo.Bingo;
import f_thon.godlifebingo.core.bingo.BingoRepository;
import f_thon.godlifebingo.core.godlife.GodLife;
import f_thon.godlifebingo.core.godlife.GodLifeRepository;
import f_thon.godlifebingo.core.users.UserRole;
import f_thon.godlifebingo.core.users.Users;
import f_thon.godlifebingo.core.users.UsersRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@EnableWebMvc
@AutoConfigureMockMvc(print = MockMvcPrint.NONE)
@SpringBootTest
@Transactional
@DisplayName("[통합 테스트] CellController")
class CellControllerTest {

    @Autowired
    MockMvc mockMvc;

    @PersistenceContext
    EntityManager em;
    @Autowired
    private CellController cellController;

    Users users;
    Bingo bingo;
    GodLife godLife1, godLife7;
    Cell cell1, cell7;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private BingoRepository bingoRepository;
    @Autowired
    private GodLifeRepository godLifeRepository;
    @Autowired
    private CellRepository cellRepository;

    @BeforeEach
    public void beforeEach() {
        users = Users.builder()
            .username("username")
            .name("name")
            .email("email@email.com")
            .role(UserRole.USER)
            .build();

        bingo = Bingo.builder()
            .title("test bingo")
            .size(3)
            .startDate(LocalDateTime.now())
            .endDate(LocalDateTime.now())
            .color("#00ff00")
            .totalCount(7)
            .users(users)
            .build();

        godLife1 = GodLife.builder()
            .title("아침 6시에 일어나기")
            .description("매일 아침 6시에 일어나기")
            .imageUrl("test")
            .isOneOff(true)
            .build();

        godLife7 = GodLife.builder()
            .title("아침 6시에 일어나기")
            .description("매일 아침 6시에 일어나기")
            .imageUrl("test")
            .build();

        cell1 = Cell.builder()
            .currentProgress(0)
            .godlife(godLife1)
            .bingo(bingo)
            .build();

        cell7 = Cell.builder()
            .currentProgress(0)
            .godlife(godLife7)
            .bingo(bingo)
            .build();

        usersRepository.save(users);
        bingoRepository.save(bingo);
        godLifeRepository.save(godLife1);
        godLifeRepository.save(godLife7);
        cellRepository.save(cell1);
        cellRepository.save(cell7);
    }


    @Nested
    class UpdateCell {
        @DisplayName("사용자는 자신의 셀의 진도를 채울 수 있다.")
        @Test
        public void updateCell_success() {
            // when
            cellController.updateCell(cell7.getId(), users.getId());

            // then
            Optional<Cell> findCell = cellRepository.findById(cell7.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(1, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }

        @DisplayName("빙고 기간동안 한번 업데이트 하는 셀은, 진행도를 모두 업데이트 한다.")
        @Test
        public void update_cell_all() {
            // when
            cellController.updateCell(cell1.getId(), users.getId());

            // then
            Optional<Cell> findCell = cellRepository.findById(cell1.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(7, cell1.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }


        @DisplayName("사용자는 같은 셀을 하루에 두번 업데이트 할 수 없다.")
        @Test
        @Disabled
        public void updateCell_noDupUpdate() {
            // when
            cellController.updateCell(cell7.getId(), users.getId());

            // then
            assertThrows(RuntimeException.class,
                () -> cellController.updateCell(cell7.getId(), users.getId()));

            Optional<Cell> findCell = cellRepository.findById(cell7.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(1, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }

        @DisplayName("사용자는 자신의 셀이 아니라면 진도를 채울 수 없다.")
        @Test
        public void updateCell_noOwnerSHip() {
            // when
            Users user2 = Users.builder()
                .username("user2")
                .name("user2")
                .email("email2@email.com")
                .build();

            usersRepository.save(user2);
            // then
            assertThrows(RuntimeException.class,
                () -> cellController.updateCell(cell7.getId(), user2.getId()));

            Optional<Cell> findCell = cellRepository.findById(cell7.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(0, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }
    }

    @Nested
    class RollbackCell {
        @BeforeEach
        public void beforeEach() {

        }

        @DisplayName("사용자는 자신의 셀의 진도를 롤백할 수 있다. (당일만 가능)")
        @Test
        public void rollbackCell_success() {
            // when
            cellController.updateCell(cell7.getId(), users.getId());

            Optional<Cell> findCell = cellRepository.findById(cell7.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(1, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });

            cellController.updateCell(cell7.getId(), users.getId());

            // then
            Optional<Cell> findCell2 = cellRepository.findById(cell7.getId());
            findCell2.ifPresentOrElse((foundCell) -> {
                assertEquals(0, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }

        @DisplayName("1회용 셀은 한번 롤백시 모든 진행도 (크기) 를 되돌린다.")
        @Test
        public void rollbackCell1_success() {
            // when
            cellController.updateCell(cell1.getId(), users.getId());

            Optional<Cell> findCell = cellRepository.findById(cell1.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(7, cell1.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });

            cellController.updateCell(cell1.getId(), users.getId());

            // then
            Optional<Cell> findCell2 = cellRepository.findById(cell1.getId());
            findCell2.ifPresentOrElse((foundCell) -> {
                assertEquals(0, cell1.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }

        @DisplayName("사용자는 업데이트 하지 않은 셀은 롤백할 수 없다.")
        @Test
        @Disabled
        public void rollbackCell_noDupRollBack() {
            // then
            assertThrows(RuntimeException.class,
                () -> cellController.updateCell(cell7.getId(), users.getId()));

            Optional<Cell> findCell = cellRepository.findById(cell7.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(0, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }

        @DisplayName("사용자는 자신의 셀이 아니라면 롤백을 할 수 없다.")
        @Test
        public void rollbackCell_noOwnerSHip() {
            // when
            cellController.updateCell(cell7.getId(), users.getId());

            Optional<Cell> findCell = cellRepository.findById(cell7.getId());
            findCell.ifPresentOrElse((foundCell) -> {
                assertEquals(1, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });

            Users user2 = Users.builder()
                .username("user2")
                .name("user2")
                .email("email2@email.com")
                .build();

            usersRepository.save(user2);

            // then
            assertThrows(RuntimeException.class,
                () -> cellController.updateCell(cell7.getId(), user2.getId()));

            Optional<Cell> findCell2 = cellRepository.findById(cell7.getId());
            findCell2.ifPresentOrElse((foundCell) -> {
                assertEquals(1, cell7.getCurrentProgress());
            }, () -> {
                Assertions.fail("Unreachable statement");
            });
        }
    }
}