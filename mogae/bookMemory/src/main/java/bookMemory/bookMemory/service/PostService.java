package bookMemory.bookMemory.service;

import bookMemory.bookMemory.dto.request.AddPostRequest;
import bookMemory.bookMemory.dto.request.UpdatePostRequest;
import bookMemory.bookMemory.dto.response.PostResponse;
import bookMemory.bookMemory.error.ErrorCode;
import bookMemory.bookMemory.error.exception.BusinessException;
import bookMemory.bookMemory.domain.Book;
import bookMemory.bookMemory.domain.Member;
import bookMemory.bookMemory.domain.Post;
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

    public PostResponse createPost(AddPostRequest request) {
        Member member = findMemberById(request.getMemberId());
        Book book = findBookById(request.getBookId());
        Post post = new Post(request.getTitle(), request.getPhrase(), request.getOpinion());
        post.updateMember(member);
        post.updateBook(book);
        Post savedPost = postRepository.save(post);
        return PostResponse.from(savedPost);
    }

    public PostResponse updatePost(Long postId, UpdatePostRequest request) {
        Post post = findPostById(postId);
        Book book = findBookById(request.getBookId());
        post.updatePostDetail(request.getTitle(), request.getPhrase(), request.getOpinion());
        post.updateBook(book);
        return PostResponse.from(post);
    }

    public void deletePost(Long postId) {
        Post post = findPostById(postId);
        postRepository.delete(post);
    }

    @Transactional(readOnly = true)
    public PostResponse getPost(Long postId) {
        Post post = findPostById(postId);
        return PostResponse.from(post);
    }

    private Member findMemberById(Long id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_MEMBER));
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_BOOK));
    }

    private Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new BusinessException(ErrorCode.NOT_FOUND_POST));
    }
}
