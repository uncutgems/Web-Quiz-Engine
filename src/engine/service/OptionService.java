package engine.service;

import engine.entity.Option;
import engine.entity.Quiz;
import engine.repository.OptionRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> findByQuiz(Quiz quiz, Sort sort) {
        return optionRepository.findOptionsByQuiz(quiz, sort);
    }
}
