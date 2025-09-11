package demo.jachodan.crud;

import demo.jachodan.crud.Post.Post;
import demo.jachodan.crud.Post.PostRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CrudApplicationTests {

  @Autowired
  private PostRepo postRepo;

  @Test
  void testJpa() {
    Post p1 = new Post();
    p1.setPost_title("test");
    p1.setPost_content("test content");
    p1.setCreated_at(LocalDateTime.now());
    this.postRepo.save(p1);

    Post p2 = new Post();
    p2.setPost_title("test2");
    p2.setPost_content("test content2");
    p2.setCreated_at(LocalDateTime.now());
    this.postRepo.save(p2);

//    List<Post> list = this.postRepo.findAll();
//    assertEquals(2, list.size());
//
//    Post p = list.get(0);
//    assertEquals("test", p.getPost_title());

//    Optional<Post> op = this.postRepo.findById(7);
//    if (op.isPresent()) {
//      Post p = op.get();
//      assertEquals("test", p.getPost_title());
//    }
  }

  @Test
  void contextLoads() {
  }

}
