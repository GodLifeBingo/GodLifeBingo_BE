package f_thon.godlifebingo.core.cell;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cell")
@RequiredArgsConstructor
public class CellController {

    private final CellService cellService;

    @PostMapping("/{cell_id}")
    public void updateCell(@PathVariable("cell_id") Long cellId, @CurrentUserId Long userId) {
        cellService.updateCell(cellId, userId);
    }
}
