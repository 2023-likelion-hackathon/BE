package likelion_hkt.ll_hkt_be.domain.etity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subId")
    private Long subId;

    private String subWord;

    private String subWordMeaning;
}
