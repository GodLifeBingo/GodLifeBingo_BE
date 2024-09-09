package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.core.bingo.dto.BingoCreateRequest;
import f_thon.godlifebingo.core.bingo.dto.CellRequest;
import f_thon.godlifebingo.core.cell.Cell;
import f_thon.godlifebingo.core.cell.CellRepository;
import f_thon.godlifebingo.core.godlife.GodLifeRepository;
import f_thon.godlifebingo.core.users.Users;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BingoService {

    private final BingoRepository bingoRepository;
    private final GodLifeRepository godLifeRepository;
    private final CellRepository cellRepository;

    public void create(BingoCreateRequest request) {
        // 후에 SecurityContext에서 가져오면 교체
        Users users = Users.builder().id(1L).build();

        // 빙고 추가
        Bingo bingo = Bingo.builder()
            .title(request.getTitle())
            .size(request.getSize())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .color(request.getColor())
            .totalCount(request.getEndDate().compareTo(request.getStartDate())+1)
            .users(users)
            .build();

        bingoRepository.save(bingo);

        // 셀 추가
        List<CellRequest> cellRequests = request.getCells();
        List<Cell> cells = cellRequests.stream()
            .map(cellRequest -> cellRequest.toCell(bingo))
            .toList();

        cellRepository.saveAll(cells);
    }
}
