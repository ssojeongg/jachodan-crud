package demo.jachodan.crud.Post;

import demo.jachodan.crud.err404Exception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepo postRepo;

    public List<Post> getPostList() {
        return this.postRepo.findAll();
    }

    public Post getPostById(Integer id) {
        Optional<Post> post = this.postRepo.findById(id);
        if (post.isPresent()) {
            return post.get();
        } else {
            throw new err404Exception("question not found");
        }
    }

    public void postNew(String title, String content) {
        Post p = new Post();
        p.setPost_title(title);
        p.setPost_content(content);
        p.setCreated_at(java.time.LocalDateTime.now());
        this.postRepo.save(p);
    }

}
