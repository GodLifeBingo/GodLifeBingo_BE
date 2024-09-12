package f_thon.godlifebingo.core.godlife.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GodLifeImageUrlListRequest {
    private List<GodLifeImageUrlRequest> godLifes;
}
