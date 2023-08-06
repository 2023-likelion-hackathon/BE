package likelion_hkt.ll_hkt_be.domain.repository;

import likelion_hkt.ll_hkt_be.domain.etity.CoinedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoinedWordRepository extends JpaRepository<CoinedWord,Long>{
    @Override
    Optional<CoinedWord> findById(@Param("coinedWordId") Long coinedWordId);
}


