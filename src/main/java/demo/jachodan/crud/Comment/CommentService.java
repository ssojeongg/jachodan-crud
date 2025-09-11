package demo.jachodan.crud.Comment;

import demo.jachodan.crud.Post.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class CommentService {

    private final CommentRepo commentRepo;

    public void createComment(Post post, String content) {
        Comment comment = new Comment();
        comment.setComment_content(content);
        comment.setCreated_at(LocalDateTime.now());
        comment.setPost(post);
        this.commentRepo.save(comment);
    }

}
