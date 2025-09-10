package bookMemory.bookMemory.controller.dto;

import bookMemory.bookMemory.model.Post;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시글 생성 요청 DTO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class AddPostRequest {
    @Schema(description = "회원ID", example = "1L")
    Long memberId;
    @Schema(description = "책ID", example = "2L")
    Long bookId;
    @Schema(description = "게시글 제목", example = "톨스토이에 관하여")
    String title;
    @Schema(description = "글귀", example = "사람은 무엇으로 사는가")
    String phrase;
    @Schema(description = "의견", example = "꿈으로 산다")
    String opinion;
}
