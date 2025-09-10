package bookMemory.bookMemory.dto.response;

import bookMemory.bookMemory.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Schema(description = "게시글 응답 DTO")
@Builder
@Getter
public class PostResponse {

    @Schema(description = "게시글ID", example = "1L")
    private Long id;

    @Schema(description = "게시글 제목", example = "이솝우화를 읽고서")
    private String postTitle;

    @Schema(description = "글귀", example = "사람은 무엇으로 사는가")
    private String phrase;

    @Schema(description = "의견", example = "꿈으로 산다")
    private String opinion;

    @Schema(description = "등록시간", example = "??")
    private LocalDateTime registeredAt;

    private Member member;

    private Book book;

    @AllArgsConstructor
    @Getter
    public static class Member {

        @Schema(description = "회원ID", example = "2L")
        private Long memberId;

        @Schema(description = "닉네임", example = "천재")
        private String nickName;
    }

    @AllArgsConstructor
    @Getter
    public static class Book {

        @Schema(description = "책ID", example = "??")
        private Long bookId;

        @Schema(description = "책 제목", example = "이솝우화")
        private String bookTitle;
    }

    public static PostResponse from(Post post) {
        Member member = new Member(post.getMember().getId(), post.getMember().getNickName());
        Book book = new Book(post.getBook().getId(), post.getBook().getTitle());

        return PostResponse.builder()
                .id(post.getId())
                .postTitle(post.getTitle())
                .phrase(post.getPhrase())
                .opinion(post.getOpinion())
                .registeredAt(post.getRegisteredAt())
                .member(member)
                .book(book)
                .build();
    }
}
