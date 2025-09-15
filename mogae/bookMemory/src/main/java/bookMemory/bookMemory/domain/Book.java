package bookMemory.bookMemory.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Book {
    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    private String genre;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Book(String title, String genre) {
        this.title = title;
        this.genre = genre;
    }

    public void updateAuthor(Author author) {
        this.author = author;
        author.getBooks().add(this);
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
