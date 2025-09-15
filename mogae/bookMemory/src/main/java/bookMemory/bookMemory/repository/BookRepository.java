package bookMemory.bookMemory.repository;

import bookMemory.bookMemory.domain.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
