package bookMemory.bookMemory.error.response;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ValidationErrorResponse extends ErrorResponse{
    private final Map<String, String> errors = new HashMap<>();

    public ValidationErrorResponse() {
        super("BAD_REQUEST", "요청값이 유효하지 않습니다.");
    }

    public void addError(String field, String message) {
        this.errors.put(field, message);
    }
}
