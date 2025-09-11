package demo.jachodan.crud.Comment;

import demo.jachodan.crud.Post.Post;
import demo.jachodan.crud.Post.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/comment")
@RequiredArgsConstructor
@Controller
public class CommentController {

    private final PostService postService;
    private final CommentService commentService;

    @PostMapping("/new/{id}")
    public String createComment(
            @PathVariable("id") Integer id,
//            @RequestParam(value="content") String content,
            @Valid CommentForm commentForm,
            BindingResult result,
            Model model) {
        Post post = this.postService.getPostById(id);

        if (result.hasErrors()) {
          model.addAttribute("post", post);
          return "postDetail";
        }

        this.commentService.createComment(post, commentForm.getContent());
        return String.format("redirect:/post/detail/%d", id);
    }

}
