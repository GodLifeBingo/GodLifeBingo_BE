package f_thon.godlifebingo.core.cell;

import f_thon.godlifebingo.common.entity.BaseEntity;
import f_thon.godlifebingo.core.godlife.GodLife;
import f_thon.godlifebingo.core.bingo.Bingo;
import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Entity
public class Cell extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int currentProgress;
    private int rowNum;
    private int colNum;
    private boolean isClicked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bingo_id")
    private Bingo bingo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "godlife_id")
    private GodLife godlife;

    public void updateProgress() {
        this.currentProgress++;
    }

    public void rollbackProgress() {
        this.currentProgress--;
    }
    public void setClicked(boolean isClicked){
        this.isClicked = isClicked;
    }
}
