package f_thon.godlifebingo.core.godlife;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/godlifes")
@RequiredArgsConstructor
public class GodLifeController {

    private final GodLifeService godLifeService;

    @GetMapping
    public SimpleGodLifeListResponse getGodLifeSimple() {
        SimpleGodLifeListResponse allGodLifesSimple = godLifeService.getAllGodLifesSimple();

        return allGodLifesSimple;
    }
}
