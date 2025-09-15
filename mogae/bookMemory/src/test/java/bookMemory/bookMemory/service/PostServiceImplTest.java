package bookMemory.bookMemory.service;

import bookMemory.bookMemory.dto.request.AddPostRequest;
import bookMemory.bookMemory.domain.Book;
import bookMemory.bookMemory.domain.Member;
import bookMemory.bookMemory.domain.Post;
import bookMemory.bookMemory.repository.BookRepository;
import bookMemory.bookMemory.repository.MemberRepository;
import bookMemory.bookMemory.repository.PostRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PostServiceImplTest {
    @Autowired PostService postService;
    @Autowired PostRepository postRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPost() {
        Member member = memberRepository.save(new Member("a", "a", "a"));
        Book book = bookRepository.save(new Book("1L", "a"));
        AddPostRequest request = new AddPostRequest(member.getId(), book.getId(), "톨", "스", "토");
        Post post = postService.createPost(request);
        assertTrue(postRepository.findById(post.getId()).isPresent());
    }

    @Test
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void getPost() {
    }
}