package demo.jachodan.crud.Post;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostForm {
    @NotEmpty(message = "제목 필수")
    @Size(max=50)
    private String title;

    @NotEmpty(message = "내용 필수")
    private String content;
}
