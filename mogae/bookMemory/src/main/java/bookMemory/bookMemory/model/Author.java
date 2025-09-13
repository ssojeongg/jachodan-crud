package bookMemory.bookMemory.model;

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
public class Author {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String gender;

    private String nation;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Book> books = new ArrayList<>();

    @Builder
    public Author(String name, String gender, String nation) {
        this.name = name;
        this.gender = gender;
        this.nation = nation;
    }

    public void addBook(Book book) {
        this.books.add(book);
    }
}