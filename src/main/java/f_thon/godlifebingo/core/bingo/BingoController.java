package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import f_thon.godlifebingo.core.bingo.dto.BingoCreateRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoGetResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoListResponse;
import f_thon.godlifebingo.core.cell.dto.BingoCellResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bingo")
public class BingoController {

    private final BingoService bingoService;

    @PostMapping
    public void create(@RequestBody BingoCreateRequest request){
        bingoService.create(request);
    }

    @GetMapping("/{bingoId}")
    public BingoGetResponse get(@PathVariable("bingoId") Long bingoId){
        return bingoService.get(bingoId);
    }

    @GetMapping("/list")
    public BingoListResponse getList(@RequestParam long limit, @RequestParam long offset){
        return bingoService.getList(limit, offset);
    }

    @GetMapping("/{bingo_id}/cells")
    public BingoCellResponse getBingoCells(@PathVariable("bingo_id") Long bingoId, @CurrentUserId Long userId) {
        return bingoService.getBingoCells(bingoId, userId);
    }
}
