package bookMemory.bookMemory.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String title;
    private String phrase;
    private String opinion;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime registeredAt;

    @LastModifiedDate
    private LocalDateTime editedAt;

    public static Post createPost(Member member, Book book, String title, String phrase, String opinion) {
        Post post = new Post(title, phrase, opinion);
        post.updateMember(member);
        post.updateBook(book);
        return post;
    }

    public Post(String title, String phrase, String opinion) {
        this.title = title;
        this.phrase = phrase;
        this.opinion = opinion;
    }

    public void updatePost(Book book, String phrase, String opinion) {
        this.updateBook(book);
        this.updatePhrase(phrase);
        this.updateOpinion(opinion);
    }
    public void updateMember(Member member) {
        this.member = member;
        if (!member.getPosts().contains(this)) {
            member.getPosts().add(this);
        }
    }

    public void updateBook(Book book) {
        this.book = book;
        if (!book.getPosts().contains(this)) {
            book.getPosts().add(this);
        }
    }

    public void updatePhrase(String phrase) {
        this.phrase = phrase;
    }

    public void updateOpinion(String opinion) {
        this.opinion = opinion;
    }
}
