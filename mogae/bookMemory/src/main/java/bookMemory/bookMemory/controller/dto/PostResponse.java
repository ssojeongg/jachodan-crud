package bookMemory.bookMemory.controller.dto;

import bookMemory.bookMemory.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Schema(description = "게시글 응답 DTO")
@Builder
@Getter
public class PostResponse {
    @Schema(description = "게시글ID", example = "1L")
    Long id;
    @Schema(description = "게시글 제목", example = "이솝우화를 읽고서")
    String postTitle;
    @Schema(description = "회원ID", example = "2L")
    Long memberId;
    @Schema(description = "닉네임", example = "천재")
    String nickName;
    @Schema(description = "책ID", example = "??")
    Long bookId;
    @Schema(description = "책 제목", example = "이솝우화")
    String bookTitle;
    @Schema(description = "글귀", example = "사람은 무엇으로 사는가")
    String phrase;
    @Schema(description = "의견", example = "꿈으로 산다")
    String opinion;
    @Schema(description = "등록시간", example = "??")
    LocalDateTime registeredAt;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .id(post.getId())
                .postTitle(post.getTitle())
                .memberId(post.getMember().getId())
                .nickName(post.getMember().getNickName())
                .bookId(post.getBook().getId())
                .bookTitle(post.getTitle())
                .phrase(post.getPhrase())
                .opinion(post.getOpinion())
                .registeredAt(post.getRegisteredAt())
                .build();
    }
}
