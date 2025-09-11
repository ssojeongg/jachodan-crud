package demo.jachodan.crud.Post;

import demo.jachodan.crud.Comment.Comment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer post_id;

  @Column(length = 50)
  private String post_title;

  @Column(columnDefinition = "TEXT")
  private String post_content;

  private LocalDateTime created_at;

  @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
  private List<Comment> comments;
}
