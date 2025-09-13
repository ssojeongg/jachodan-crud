package bookMemory.bookMemory.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    private String nickName;

    private LocalDate birthDate;

    private String gender;

    private String address;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Post> posts = new ArrayList<>();

    @Builder
    public Member(String nickName, LocalDate birthDate, String gender, String address) {
        this.nickName = nickName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}
