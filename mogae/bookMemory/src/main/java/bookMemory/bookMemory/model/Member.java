package bookMemory.bookMemory.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String nickName;
    private String age;
    private String gender;
    private LocalDateTime joinedAt;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();

    public Member(String nickName, String age, String gender) {
        this.nickName = nickName;
        this.age = age;
        this.gender = gender;
    }

    public void addPost(Post post) {
        this.posts.add(post);
        post.updateMember(this);
    }
}
