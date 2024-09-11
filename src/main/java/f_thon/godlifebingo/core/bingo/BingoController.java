package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import f_thon.godlifebingo.core.cell.BingoCellResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bingo")
@RequiredArgsConstructor
public class BingoController {

    private final BingoService bingoService;


    @GetMapping("/{bingo_id}")
    public BingoCellResponse getBingoCells(@PathVariable("bingo_id") Long bingoId, @CurrentUserId Long userId) {
        return bingoService.getBingoCells(bingoId, userId);
    }
}
