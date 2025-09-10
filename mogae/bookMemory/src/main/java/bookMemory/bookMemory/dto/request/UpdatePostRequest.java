package bookMemory.bookMemory.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(description = "게시글 수정 요청 DTO")
@NoArgsConstructor
@Getter @Setter
public class UpdatePostRequest {

    @Schema(description = "게시글ID", example = "1L")
    @NotNull(message = "게시글ID는 필수 값 입니다.")
    private Long postId;

    @Schema(description = "책ID", example = "2L")
    @NotNull(message = "책ID는 필수 값 입니다.")
    private Long bookId;

    @Schema(description = "책 구절", example = "사람은 무엇으로 사는가")
    @NotBlank(message = "책 구절은 필수 값 입니다.")
    private String phrase;

    @Schema(description = "의견", example = "꿈으로 산다")
    @NotBlank(message = "의견은 필수 값 입니다.")
    private String opinion;
}
