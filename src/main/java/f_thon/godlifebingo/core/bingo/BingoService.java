package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.core.bingo.dto.BingoCreateResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoGetResponse;
import f_thon.godlifebingo.core.cell.BingoCellInfo;
import f_thon.godlifebingo.core.cell.BingoCellResponse;
import f_thon.godlifebingo.core.cell.Cell;
import f_thon.godlifebingo.core.cell.CellRepository;
import f_thon.godlifebingo.core.godlife.GodLife;
import f_thon.godlifebingo.core.godlife.GodLifeRepository;
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
