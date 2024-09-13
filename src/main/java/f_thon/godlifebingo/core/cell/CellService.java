package f_thon.godlifebingo.core.cell;

import f_thon.godlifebingo.core.CellHistory.CellHistory;
import f_thon.godlifebingo.core.CellHistory.CellHistoryRepository;
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

    @Transactional
    public void updateCell(Long cellId, Long userId) {
        Cell cell = checkCellOwnership(cellId, userId);
        /*
        금일 히스토리를 올린 적이 있는지
         */

        boolean exists = cellHistoryRepository.existsByCellAndCreatedAt(cell);

        if (exists) {
            log.error("금일 cell 업데이트를 했습니다. cell id : {}", cellId);
            throw new RuntimeException();
        }

        CellHistory cellHistory = CellHistory.builder().isClicked(true).cell(cell).build();

        cellHistoryRepository.save(cellHistory);

        cell.updateProgress();
        cell.setClicked(true);
    }


    @Transactional
    public void rollbackCell(Long cellId, Long userId) {
        Cell cell = checkCellOwnership(cellId, userId);
        Optional<CellHistory> optionalCellHistory = cellHistoryRepository.findByCellAndCreatedAt(
            cell);

        optionalCellHistory.ifPresentOrElse((cellHistory) -> {
            cellHistoryRepository.delete(cellHistory);
            cell.rollbackProgress();
            cell.setClicked(false);
        }, () -> {
            log.error("금일 갱신한 cell 정보가 없습니다. cell id : {}", cellId);
            throw new RuntimeException();
        });
    }

    private Cell checkCellOwnership(Long cellId, Long userId) {
        /*
        check ownership
         */
        Optional<Cell> optionalCell = cellRepository.findById(cellId);

        Cell cell = optionalCell.orElseThrow(() -> {
            log.error("ID로 cell을 찾을 수 없습니다. cell id : {}", cellId);
            throw new RuntimeException();
        });

        if (cell.getBingo().getUsers().getId() != userId) {
            log.error("cell의 접근 권한이 없습니다. cell id : {}, user id : {}", cellId, userId);
            throw new RuntimeException();
        }
        return cell;
    }
}
