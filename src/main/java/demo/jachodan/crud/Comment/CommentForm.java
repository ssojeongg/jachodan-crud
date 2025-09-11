package demo.jachodan.crud.Comment;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {
    @NotEmpty(message = "답변 내용필수")
    private String content;
}
