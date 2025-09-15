package bookMemory.bookMemory.domain;

import bookMemory.bookMemory.vo.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post extends BaseEntity {
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

    public Post(String title, String phrase, String opinion) {
        this.title = title;
        this.phrase = phrase;
        this.opinion = opinion;
    }

    public void updatePostDetail(String title, String phrase, String opinion) {
        this.title = title;
        this.phrase = phrase;
        this.opinion = opinion;
    }

    public void updateMember(Member member) {
        this.member = member;
        member.getPosts().add(this);
    }

    public void updateBook(Book book) {
        this.book = book;
        book.getPosts().add(this);
    }
}
