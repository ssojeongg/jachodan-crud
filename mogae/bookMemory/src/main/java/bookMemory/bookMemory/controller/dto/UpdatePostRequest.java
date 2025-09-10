package bookMemory.bookMemory.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "게시글 수정 요청 DTO")
@NoArgsConstructor
@Getter
public class UpdatePostRequest {
    @Schema(description = "게시글ID", example = "1L")
    Long postId;
    @Schema(description = "책ID", example = "2L")
    Long bookId;
    @Schema(description = "글귀", example = "사람은 무엇으로 사는가")
    String phrase;
    @Schema(description = "의견", example = "꿈으로 산다")
    String opinion;
}
