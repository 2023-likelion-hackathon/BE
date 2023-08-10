package likelion_hkt.ll_hkt_be.domain.repository;

import likelion_hkt.ll_hkt_be.domain.etity.SearchedWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface SearchedWordRepository extends JpaRepository<SearchedWord, Long> {
    List<SearchedWord> findTop20ByOrderBySearchedId();

    List<SearchedWord> findAll();
}
