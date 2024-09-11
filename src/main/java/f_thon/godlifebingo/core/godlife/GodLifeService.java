package f_thon.godlifebingo.core.godlife;

import f_thon.godlifebingo.core.godlife.SimpleGodLifeListResponse.SimpleGodLifeResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GodLifeService {

    private final GodLifeRepository godLifeRepository;

    public SimpleGodLifeListResponse getAllGodLifesSimple() {
        List<GodLife> godlifes = godLifeRepository.findAll();

        List<SimpleGodLifeResponse> simpleGodlifes = godlifes
            .stream()
            .map(godlife -> SimpleGodLifeResponse.builder()
                .id(godlife.getId())
                .imageUrl(godlife.getImageUrl())
                .title(godlife.getTitle())
                .build())
            .toList();

        SimpleGodLifeListResponse godLifeListResponse = SimpleGodLifeListResponse.builder()
            .godLifes(simpleGodlifes)
            .build();

        return godLifeListResponse;
    }
}
