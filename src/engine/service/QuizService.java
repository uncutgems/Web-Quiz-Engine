package engine.service;

import engine.entity.Quiz;
import engine.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizService {
    private final QuizRepository quizRepository;
    private Quiz quiz;

    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public Optional<Quiz> findById(int id) {
        return quizRepository.findById(id);
    }

    public Quiz saveQuiz(Quiz quiz){
        return quiz.getQuestionOnly(quizRepository.save(quiz));
    }

    public void deleteQuiz(int id){
        quizRepository.deleteById(id);
    }

    public List<Quiz> findAll() {
        return (List<Quiz>)quizRepository.findAll();
    }
}
