package bookMemory.bookMemory.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private String title;

    private String phrase;

    private String opinion;

    public static Post createPost(Member member, Book book, String title, String phrase, String opinion) {
        Post post = new Post(title, phrase, opinion);
        post.updateMember(member);
        post.updateBook(book);
        return post;
    }

    private Post(String title, String phrase, String opinion) {
        this.title = title;
        this.phrase = phrase;
        this.opinion = opinion;
    }

    public void updatePost(Book book, String title, String phrase, String opinion) {
        this.updateBook(book);
        this.updateTitle(title);
        this.updatePhrase(phrase);
        this.updateOpinion(opinion);
    }
    public void updateMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public void updateBook(Book book) {
        this.book = book;
        book.getPosts().add(this);
    }

    public void updateTitle(String title) {
        this.title = title;
    }

    public void updatePhrase(String phrase) {
        this.phrase = phrase;
    }

    public void updateOpinion(String opinion) {
        this.opinion = opinion;
    }
}
