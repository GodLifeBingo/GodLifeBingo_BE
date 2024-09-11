package f_thon.godlifebingo.core.godlife;

import f_thon.godlifebingo.core.godlife.SimpleGodLifeListResponse.SimpleGodLifeResponse;
import f_thon.godlifebingo.core.users.Users;
import f_thon.godlifebingo.core.users.UsersRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GodLifeService {

    private final GodLifeRepository godLifeRepository;
    private final UsersRepository usersRepository;

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

        return SimpleGodLifeListResponse.builder()
            .godLifes(simpleGodlifes)
            .build();
    }

    public GodLifeStatisticsListResponse getGodLifeStatisticsList(String searchTitle,
        Pageable pageable, Long userId) {
        Users user = usersRepository.getReferenceById(userId);

        Page<GodLifeStatistic> godLifeStatistics = godLifeRepository.getGodLifeStatistics(
            searchTitle, user, pageable);

        return GodLifeStatisticsListResponse.builder()
            .godlifes(godLifeStatistics.getContent())
            .page(pageable.getPageNumber())
            .size(pageable.getPageSize())
            .totalPage(godLifeStatistics.getTotalPages())
            .totalCount(godLifeStatistics.getTotalElements())
            .build();
    }
}
