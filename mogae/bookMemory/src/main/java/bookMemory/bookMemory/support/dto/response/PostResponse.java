package bookMemory.bookMemory.support.dto.response;

import bookMemory.bookMemory.domain.Book;
import bookMemory.bookMemory.domain.Member;
import bookMemory.bookMemory.domain.Post;
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

    private MemberDto memberDto;

    private BookDto bookDto;

    @AllArgsConstructor
    @Getter
    public static class MemberDto {

        @Schema(description = "회원ID", example = "2L")
        private Long memberId;

        @Schema(description = "닉네임", example = "천재")
        private String nickName;
    }

    @AllArgsConstructor
    @Getter
    public static class BookDto {

        @Schema(description = "책ID", example = "??")
        private Long bookId;

        @Schema(description = "책 제목", example = "이솝우화")
        private String bookTitle;
    }

    public static PostResponse from(Post post) {
        Member member = post.getMember();
        Book book = post.getBook();

        MemberDto memberDto = new MemberDto(member.getId(), member.getNickName());
        BookDto bookDto = new BookDto(book.getId(), book.getTitle());

        return PostResponse.builder()
                .id(post.getId())
                .postTitle(post.getTitle())
                .phrase(post.getPhrase())
                .opinion(post.getOpinion())
                .registeredAt(post.getRegisteredAt())
                .memberDto(memberDto)
                .bookDto(bookDto)
                .build();
    }
}
