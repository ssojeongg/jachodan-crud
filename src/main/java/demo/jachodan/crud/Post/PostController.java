package demo.jachodan.crud.Post;

import demo.jachodan.crud.Comment.CommentForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/post")
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostRepo postRepo;
    private final PostService postService;

    @GetMapping("/list")
//    @ResponseBody
    public String postList(Model model) {
//        List<Post> postList = this.postRepo.findAll();
        List<Post> postList = this.postService.getPostList();
        model.addAttribute("postList", postList);
        return "postList";
    }

    @GetMapping("/detail/{id}")
    public String postDetail(@PathVariable("id") Integer id, CommentForm commentForm, Model model) {
        Post post = this.postService.getPostById(id);
        model.addAttribute("post", post);
        return "postDetail";
    }

    @GetMapping("/new")
    public String postNew(PostForm postForm, Model model) {
        return "postForm";
    }

    @PostMapping("/new")
    public String postNew(
//            @RequestParam(value="title") String title,
//            @RequestParam(value="content") String content,
            @Valid PostForm postForm,
            BindingResult result,
            Model model) {
      if (result.hasErrors()) {
        return "postForm";
      }
//      this.postService.postNew(title, content);
      this.postService.postNew(postForm.getTitle(), postForm.getContent());
      return "redirect:/post/list";
    }

}
