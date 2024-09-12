package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.core.bingo.dto.BingoCreateRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoCreateResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoGetResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoListRequest;
import f_thon.godlifebingo.core.cell.dto.BingoCellInfo;
import f_thon.godlifebingo.core.cell.dto.BingoCellResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoListResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoRow;
import f_thon.godlifebingo.core.bingo.dto.CellRequest;
import f_thon.godlifebingo.core.bingo.dto.CellResponse;
import f_thon.godlifebingo.core.cell.Cell;
import f_thon.godlifebingo.core.cell.CellRepository;
import f_thon.godlifebingo.core.godlife.GodLifeRepository;
import f_thon.godlifebingo.core.users.Users;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class BingoService {

    private final BingoRepository bingoRepository;
    private final GodLifeRepository godLifeRepository;
    private final CellRepository cellRepository;

    public BingoCreateResponse create(Long userId, BingoCreateRequest request) {
        // 빙고 추가
        Bingo bingo = Bingo.builder()
            .title(request.getTitle())
            .size(request.getSize())
            .startDate(request.getStartDate())
            .endDate(request.getEndDate())
            .color(request.getColor())
            .totalCount(request.getEndDate().compareTo(request.getStartDate())+1)
            .users(Users.builder().id(userId).build())
            .build();

        Bingo savedBingo = bingoRepository.save(bingo);

        // 셀 추가
        List<CellRequest> cellRequests = request.getCells();
        List<Cell> cells = cellRequests.stream()
            .map(cellRequest -> cellRequest.toCell(bingo))
            .toList();

        cellRepository.saveAll(cells);

        return BingoCreateResponse.builder()
            .bingoId(savedBingo.getId())
            .build();
    }

    public BingoGetResponse get(Long userId, Long bingoId) {

        Bingo bingo = bingoRepository.findById(bingoId).orElseThrow();
        if(userId != bingo.getUsers().getId()){
            throw new RuntimeException();
        }

        List<Cell> cells = cellRepository.findByBingoId(bingoId);
        List<CellResponse> cellResponses = cells.stream()
            .map(CellResponse::of)
            .toList();

        BingoGetResponse response = BingoGetResponse.builder()
            .bingo(BingoResponse.of(bingo))
            .cells(cellResponses)
            .build();
        return response;
    }

    public BingoListResponse getList(Long userId, BingoListRequest request) {
        return bingoRepository.getList(userId, request);
    }

    public BingoCellResponse getBingoCells(Long bingoId, Long userId) {
        Bingo bingo = checkBingoOwnership(bingoId, userId);

        List<Cell> cells = cellRepository.findAllByBingo(bingo);

        List<BingoCellInfo> bingoCellInfos = cells.stream().map(cell -> BingoCellInfo.builder()
                .title(cell.getGodlife().getTitle())
                .id(cell.getId())
                .achievementRate((double) cell.getCurrentProgress() / (double) bingo.getTotalCount())
                .row(cell.getRowNum())
                .col(cell.getColNum())
                .build())
            .toList();

        return BingoCellResponse.builder()
            .bingoCells(bingoCellInfos)
            .build();
    }

    private Bingo checkBingoOwnership(Long bingoId, Long userId) {
        /*
        check ownership
         */
        Optional<Bingo> optionalBingo = bingoRepository.findById(bingoId);

        Bingo bingo = optionalBingo.orElseThrow(() -> {
            log.error("ID로 bingo 를 찾을 수 없습니다. bingo id : {}", bingoId);
            throw new RuntimeException();
        });

        if (bingo.getUsers().getId() != userId) {
            log.error("bingo 의 접근 권한이 없습니다. bingo id : {}, user id : {}", bingoId, userId);
            throw new RuntimeException();
        }
        return bingo;
    }

}
