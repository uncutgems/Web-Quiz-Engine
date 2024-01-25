package engine.repository;

import engine.entity.Option;
import engine.entity.Quiz;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OptionRepository extends CrudRepository<Option, Integer> {
    List<Option> findOptionsByQuiz(Quiz quiz, Sort sort);
}
