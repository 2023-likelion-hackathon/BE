package likelion_hkt.ll_hkt_be.domain.etity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchedWord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "searchedId")
    private Long searchedId;

    private String coinedWord;

    private String subWord;


}
