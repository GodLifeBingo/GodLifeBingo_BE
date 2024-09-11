package f_thon.godlifebingo.core.godlife;

import f_thon.godlifebingo.common.annotation.CurrentUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/godlifes")
@RequiredArgsConstructor
public class GodLifeController {

    private final GodLifeService godLifeService;

    @GetMapping
    public SimpleGodLifeListResponse getGodLifeSimple() {
        return godLifeService.getAllGodLifesSimple();
    }

    @GetMapping("/statistics")
    public GodLifeStatisticsListResponse getGodLifeSimple(@RequestParam("query") String query,
        @PageableDefault Pageable pageable, @CurrentUserId Long userId) {
        return godLifeService.getGodLifeStatisticsList(query, pageable, userId);
    }
}
