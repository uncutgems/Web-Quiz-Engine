package engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "quiz")
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Text is required")
    private String text;

    @OneToMany(mappedBy = "answer", cascade = CascadeType.REMOVE)
    private List<Answer> answer;

    @OneToMany(mappedBy = "option", cascade = CascadeType.REMOVE)
    @Size(min = 2)
    private List<Option> option;

    @ManyToOne
    @JoinColumn(name = "userId")
    @JsonIgnore
    private User user;

    public List<Option> getOption() {
        return option;
    }

    public List<Answer> getAnswer() {
        return answer;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setOption(List<Option> option) {
        this.option = option;
    }

    public Quiz getQuestionOnly(Quiz quiz){
        Quiz question = new Quiz();
        question.setId(quiz.getId());
        question.setTitle(quiz.getTitle());
        question.setText(quiz.getText());
        question.setOption(quiz.getOption());

        return question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
