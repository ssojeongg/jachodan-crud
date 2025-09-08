package first.crudPrac.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts") // 공통 주소
public class PostController {

    @PostMapping
    public String createPost() {
        return "글 생성";
    }

    @GetMapping
    public String getPosts() {
        return "글 목록";
    }

    @GetMapping("/{id}")
    public String getPost(@PathVariable Long id) {
        return "특정 글 조회: " + id;
    }

    @PutMapping("/{id}")
    public String updatePost(@PathVariable Long id) {
        return "글 수정: " + id;
    }

    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        return "글 삭제: " + id;
    }
}
