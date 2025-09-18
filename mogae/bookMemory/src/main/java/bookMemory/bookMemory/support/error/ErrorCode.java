package bookMemory.bookMemory.support.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    BAD_REQUEST("BAD_REQUEST", "요청값이 유효하지 않습니다.", HttpStatus.BAD_REQUEST),
    NOT_FOUND_MEMBER("NOT_FOUND_MEMBER", "존재하지 않는 회원입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_BOOK("NOT_FOUND_BOOK", "존재하지 않는 책입니다.", HttpStatus.NOT_FOUND),
    NOT_FOUND_POST("NOT_FOUND_POST", "존재하지 않는 게시글입니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus status;

    ErrorCode(String code, String message, HttpStatus status) {
        this.code = code;
        this.message = message;
        this.status = status;
    }
}
