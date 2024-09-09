package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.core.bingo.dto.BingoCreateResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoGetResponse;
import f_thon.godlifebingo.core.cell.Cell;
import f_thon.godlifebingo.core.cell.CellRepository;
import f_thon.godlifebingo.core.godlife.GodLife;
import f_thon.godlifebingo.core.godlife.GodLifeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BingoService {

    private final BingoRepository bingoRepository;
    private final GodLifeRepository godLifeRepository;
    private final CellRepository cellRepository;

}
