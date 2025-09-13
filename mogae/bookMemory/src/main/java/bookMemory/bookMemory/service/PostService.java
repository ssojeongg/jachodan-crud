package bookMemory.bookMemory.service;

import bookMemory.bookMemory.dto.request.AddPostRequest;
import bookMemory.bookMemory.dto.request.UpdatePostRequest;
import bookMemory.bookMemory.error.exception.NotFoundException;
import bookMemory.bookMemory.model.Book;
import bookMemory.bookMemory.model.Member;
import bookMemory.bookMemory.model.Post;
import bookMemory.bookMemory.repository.BookRepository;
import bookMemory.bookMemory.repository.MemberRepository;
import bookMemory.bookMemory.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {

    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final PostRepository postRepository;

    public Post createPost(AddPostRequest request) {
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_MEMBER", "존재하지 않는 회원입니다. id=" + request.getMemberId()));
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_BOOK", "존재하지 않는 책입니다. id=" + request.getBookId()));
        Post post = Post.createPost(member, book, request.getTitle(), request.getPhrase(), request.getOpinion());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequest request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_BOOK", "존재하지 않는 책입니다. id=" + request.getBookId()));
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_POST", "존재하지 않는 게시글입니다. id=" + request.getPostId()));
        post.updatePost(book, request.getPhrase(), request.getOpinion());
        return post;
    }

    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    public Post getPost(Long postId) {
        return postRepository.findById(postId).orElseThrow();
    }
}
