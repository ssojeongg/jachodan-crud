package first.crudPrac.controller;

import first.crudPrac.model.Post;
import first.crudPrac.service.PostService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class WebController {

    private final PostService postService;

    public WebController(PostService postService) {
        this.postService = postService;
    }

    // 목록
    @GetMapping("/")
    public String home(Model model) {
        // 첫 번째 페이지, 10개씩, ID 기준 내림차순 정렬
        Pageable pageable = PageRequest.of(0, 10, Sort.by("id").descending());
        model.addAttribute("posts", postService.getPosts(pageable));
        return "main";
    }

    // 글쓰기 페이지
    @GetMapping("/posts/write")
    public String writeForm() {
        return "writeForm";
    }

    // 글 상세
    @GetMapping("/posts/{id}")
    public String detailForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id).orElse(null));
        return "detailForm";
    }
    // 글 작성 처리 - 메인페이지로 리다이렉트
    @PostMapping("/post")
    public String createPost(@ModelAttribute Post post) {
        postService.createPost(post);
        return "redirect:/";
    }

    // 글 수정 페이지
    @GetMapping("/posts/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("post", postService.getPost(id).orElse(null));
        return "editForm";
    }

    // 글 수정 처리
    @PostMapping("/posts/{id}")
    public String updatePost(@PathVariable Long id, Post updatedPost) {
        postService.updatePost(id, updatedPost);
        return "redirect:/posts/" + id;
    }

    // 글 삭제
    @GetMapping("/posts/{id}/delete")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/";
    }
}
