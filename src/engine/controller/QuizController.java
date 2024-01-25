package engine.controller;

import engine.entity.Answer;
import engine.entity.Quiz;
import engine.entity.Response;
import engine.service.QuizService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/quizzes")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping("{id}/solve")
    public ResponseEntity<?> getAnswer(@PathVariable int id, @RequestParam List<Answer> answer){
        if(quizService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Quiz quiz = quizService.findById(id).get();
        if (quiz.getAnswer().equals(answer)){
            return new ResponseEntity<>(new Response(true, "No cap"),
                    HttpStatus.OK);
        }
        return new ResponseEntity<>(new Response(true, "Cap"), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createNewQuiz(@Valid @RequestBody Quiz quiz){
        return new ResponseEntity<>(quizService.saveQuiz(quiz), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getQuiz(@PathVariable int id){
        if (quizService.findById(id).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                new Quiz().getQuestionOnly(quizService.findById(id).get()), HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllQuizzes(){
        List<Quiz> quizzes = quizService.findAll();
        quizzes.forEach(quiz -> {
            quiz = new Quiz().getQuestionOnly(quiz);
        });

        return new ResponseEntity<>(quizzes, HttpStatus.OK);
    }
}
