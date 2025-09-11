package demo.jachodan.crud.Comment;

import demo.jachodan.crud.Post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Comment {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer comment_id;

  @Column(columnDefinition = "TEXT")
  private String comment_content;

  private LocalDateTime created_at;

  @ManyToOne
  private Post post;
}
