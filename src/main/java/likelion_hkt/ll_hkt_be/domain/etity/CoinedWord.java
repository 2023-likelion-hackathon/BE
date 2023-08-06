package likelion_hkt.ll_hkt_be.domain.etity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CoinedWord {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coinedId")
    private Long coinedId;

    private String coinedWord;

    private String coinedMeaning;

    private String coinedUrl;
}
