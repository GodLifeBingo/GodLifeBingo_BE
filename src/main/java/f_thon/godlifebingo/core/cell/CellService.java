package f_thon.godlifebingo.core.cell;

import f_thon.godlifebingo.core.CellHistory.CellHistory;
import f_thon.godlifebingo.core.CellHistory.CellHistoryRepository;
import f_thon.godlifebingo.core.bingo.Bingo;
import f_thon.godlifebingo.core.bingo.BingoRepository;
import f_thon.godlifebingo.core.godlife.GodLife;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CellService {

    private final CellRepository cellRepository;
    private final CellHistoryRepository cellHistoryRepository;
    private final BingoRepository bingoRepository;

    @Transactional
    public Cell updateCell(Long cellId, Long userId) {
        Cell cell = checkCellOwnership(cellId, userId);
        Bingo bingo = cell.getBingo();
        GodLife godLife = cell.getGodlife();

        if (bingo == null) {
            log.error("셀과 연결된 빙고를 찾을 수 없습니다.", cellId);
            throw new RuntimeException();
        }

        if (godLife == null) {
            log.error("셀과 연결된 godlife를 찾을 수 없습니다..", cellId);
            throw new RuntimeException();
        }

        if (cell.isClicked()) {
            Optional<CellHistory> optionalCellHistory = cellHistoryRepository.findByCellAndCreatedAt(
                cell);

            optionalCellHistory.ifPresentOrElse((cellHistory) -> {
                cellHistoryRepository.delete(cellHistory);
                cell.rollbackProgress(bingo, godLife);
                cell.setClicked(false);
            }, () -> {
                log.error("금일 갱신한 cell 정보가 없습니다. cell id : {}", cellId);
                throw new RuntimeException();
            });
        } else {
            boolean exists = cellHistoryRepository.existsByCellAndCreatedAt(cell);

            if (exists) {
                log.error("금일 cell 업데이트를 했습니다. cell id : {}", cellId);
                throw new RuntimeException();
            }

            if (cell.getGodlife().isOneOff() && cell.getCurrentProgress() == 1) {
                log.error("이미 완료한 셀 입니다. cell id : {}", cellId);
                throw new RuntimeException();
            }

            CellHistory cellHistory = CellHistory.builder().isClicked(true).cell(cell).build();
            cellHistoryRepository.save(cellHistory);

            cell.updateProgress(bingo, godLife);
            cell.setClicked(true);
        }

        bingoRepository.updateTotalCompletedRate();
        return cell;
    }

    private Cell checkCellOwnership(Long cellId, Long userId) {
        Optional<Cell> optionalCell = cellRepository.findById(cellId);

        Cell cell = optionalCell.orElseThrow(() -> {
            log.error("ID로 cell을 찾을 수 없습니다. cell id : {}", cellId);
            throw new RuntimeException();
        });

        if (!cell.getBingo().getUsers().getId().equals(userId)) {
            log.error("cell의 접근 권한이 없습니다. cell id : {}, user id : {}", cellId, userId);
            throw new RuntimeException();
        }
        return cell;
    }
}
