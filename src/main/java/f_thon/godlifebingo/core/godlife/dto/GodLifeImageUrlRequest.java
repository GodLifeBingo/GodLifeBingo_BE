package f_thon.godlifebingo.core.godlife.dto;

import f_thon.godlifebingo.core.godlife.GodLife;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GodLifeImageUrlRequest {
    private Long cell_id;
    private String title;
    private String imageUrl;

    public GodLife toGodLife(){
        return GodLife.builder()
            .title(title)
            .imageUrl(imageUrl)
            .build();
    }
}
