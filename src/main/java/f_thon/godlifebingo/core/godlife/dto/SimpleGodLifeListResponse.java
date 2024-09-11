package f_thon.godlifebingo.core.godlife.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SimpleGodLifeListResponse {
    private List<SimpleGodLifeResponse> godLifes;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    static class SimpleGodLifeResponse {
        private String title;
        private String imageUrl;
        private Long id;
    }
}
