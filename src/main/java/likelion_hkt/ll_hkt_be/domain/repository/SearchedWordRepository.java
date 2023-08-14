package likelion_hkt.ll_hkt_be.domain.repository;

import likelion_hkt.ll_hkt_be.domain.etity.SearchedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchedWordRepository extends JpaRepository<SearchedWord, Long> {
    List<SearchedWord> findTop20ByOrderBySearchedId();

    List<SearchedWord> findAll();

    void deleteAll();
}
