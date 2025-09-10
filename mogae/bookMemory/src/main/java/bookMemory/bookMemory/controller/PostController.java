package bookMemory.bookMemory.controller;

import bookMemory.bookMemory.controller.dto.AddPostRequest;
import bookMemory.bookMemory.controller.dto.PostResponse;
import bookMemory.bookMemory.controller.dto.UpdatePostRequest;
import bookMemory.bookMemory.model.Post;
import bookMemory.bookMemory.service.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                    content = @Content(schema = @Schema(implementation = PostResponse.class))),
            @ApiResponse(
                    responseCode = "400", description = "요청값이 잘못됨",
                    content = @Content
            )
    })
    @PostMapping
    public ResponseEntity<PostResponse> addPost(@RequestBody AddPostRequest request
    ) {
        Post post = postService.createPost(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(PostResponse.from(post));
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
                    responseCode = "400", description = "요청값이 잘못됨",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "게시글을 찾을 수 없음",
                    content = @Content
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long id) {
        Post post = postService.getPost(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PostResponse.from(post));
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
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "404", description = "게시글을 찾을 수 없음",
                    content = @Content
            )
    })
    @PutMapping
    public ResponseEntity<PostResponse> updatePost(@RequestBody UpdatePostRequest request) {
        Post post = postService.updatePost(request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(PostResponse.from(post));
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
                    responseCode = "400", description = "요청값이 잘못됨",
                    content = @Content
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
