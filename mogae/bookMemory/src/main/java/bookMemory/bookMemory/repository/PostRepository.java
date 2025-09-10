package bookMemory.bookMemory.repository;

import bookMemory.bookMemory.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
