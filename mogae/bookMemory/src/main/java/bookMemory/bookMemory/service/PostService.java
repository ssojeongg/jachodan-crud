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
        Member member = findMemberById(request.getMemberId());
        Book book = findBookById(request.getBookId());
        Post post = Post.createPost(member, book, request.getTitle(), request.getPhrase(), request.getOpinion());
        return postRepository.save(post);
    }

    public Post updatePost(UpdatePostRequest request) {
        Book book = findBookById(request.getBookId());
        Post post = findPostById(request.getPostId());
        post.updatePost(book, request.getTitle(), request.getPhrase(), request.getOpinion());
        return post;
    }

    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public Post getPost(Long postId) {
        return findPostById(postId);
    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_MEMBER", "존재하지 않는 회원입니다. id=" + id));
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_BOOK", "존재하지 않는 책입니다. id=" + id));
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("NOT_FOUND_POST", "존재하지 않는 게시글입니다. id=" + id));
    }
}
