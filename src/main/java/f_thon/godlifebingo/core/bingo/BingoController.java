package f_thon.godlifebingo.core.bingo;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import f_thon.godlifebingo.core.bingo.dto.BingoCreateRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoCreateResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoGetResponse;
import f_thon.godlifebingo.core.bingo.dto.BingoListRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoListResponse;
import f_thon.godlifebingo.core.cell.dto.BingoCellResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bingo")
public class BingoController {

    private final BingoService bingoService;

    @PostMapping
    public BingoCreateResponse create(@CurrentUserId Long userId, @RequestBody BingoCreateRequest request){
        return bingoService.create(userId, request);
    }

    @GetMapping("/{bingoId}")
    public BingoGetResponse get(@CurrentUserId Long userId, @PathVariable("bingoId") Long bingoId){
        return bingoService.get(userId, bingoId);
    }

    @GetMapping("/list")
    public BingoListResponse getList(@CurrentUserId Long userId, @ModelAttribute BingoListRequest request){
        return bingoService.getList(userId, request);
    }

    @GetMapping("/{bingo_id}/cells")
    public BingoCellResponse getBingoCells(@PathVariable("bingo_id") Long bingoId, @CurrentUserId Long userId) {
        return bingoService.getBingoCells(bingoId, userId);
    }

    @PostMapping("/complete/{bingoId}")
    public String completeBingo(@CurrentUserId Long userId, @PathVariable("bingoId") Long bingoId){
        bingoService.completeBingo(userId, bingoId);
        return "Mission is completed";
    }
}
