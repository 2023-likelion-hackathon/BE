package likelion_hkt.ll_hkt_be.domain.repository;

import likelion_hkt.ll_hkt_be.domain.etity.SearchedWord;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SearchedWordRepository extends JpaRepository<SearchedWord,Long> {
    @Override
    <S extends SearchedWord> List<S> findAll(Example<S> example);
}
