package engine.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class User {
    private static final String emailRegex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-]" +
            "[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Pattern(regexp = emailRegex, message = "Invalid email")
    @Column(name = "email")
    private String email;

    @Size(min = 5, message = "The password must have at least five characters")
    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
