package f_thon.godlifebingo.api;

import f_thon.godlifebingo.core.bingo.BingoService;
import f_thon.godlifebingo.core.bingo.dto.BingoCreateRequest;
import f_thon.godlifebingo.core.bingo.dto.BingoGetResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bingo")
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
}
