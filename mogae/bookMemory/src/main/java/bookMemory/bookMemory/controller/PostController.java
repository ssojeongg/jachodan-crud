package bookMemory.bookMemory.controller;

import bookMemory.bookMemory.dto.request.AddPostRequest;
import bookMemory.bookMemory.dto.response.PostResponse;
import bookMemory.bookMemory.dto.request.UpdatePostRequest;
import bookMemory.bookMemory.error.response.ErrorResponse;
import bookMemory.bookMemory.error.response.ValidationErrorResponse;
import bookMemory.bookMemory.domain.Post;
import bookMemory.bookMemory.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/posts")
@Tag(name = "Post API", description = "게시글 관련 API")
public class PostController {

    private final PostService postService;

    @Operation(
            summary = "게시글 생성", description = "새 게시글을 생성합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "성공적으로 생성됨",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "요청값이 잘못됨",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "존재하지 않는 회원, 존재하지 않는 책",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@RequestBody @Valid AddPostRequest request
    ) {
        PostResponse response = postService.createPost(request);
        URI location = URI.create("v1/posts/" + response.getId());
        return ResponseEntity
                .created(location)
                .body(response);
    }

    @Operation(
            summary = "게시글 조회", description = "게시글id로 게시글을 조회합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "게시글 조회 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "존재하지 않는 게시글",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        PostResponse response = postService.getPost(id);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "게시글 수정", description = "게시글의 내용을 수정합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "게시글 수정 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "400", description = "요청값이 잘못됨",
                    content = @Content(schema = @Schema(implementation = ValidationErrorResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "존재하지 않는 책, 존재하지 않는 게시글",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long id,
            @RequestBody @Valid UpdatePostRequest request
    ) {
        PostResponse response = postService.updatePost(id, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "게시글 삭제",
            description = "게시글을 삭제합니다."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "게시글 삭제 성공",
                    content = @Content(schema = @Schema(implementation = PostResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404", description = "존재하지 않는 게시글",
                    content = @Content(schema = @Schema(implementation = ErrorResponse.class))
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
