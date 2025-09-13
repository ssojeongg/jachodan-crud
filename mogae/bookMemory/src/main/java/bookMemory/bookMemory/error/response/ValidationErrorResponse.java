package bookMemory.bookMemory.error.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ValidationErrorResponse {
    private final String code = "BAD_REQUEST";
    private final String message = "요청값이 유효하지 않습니다.";
    private final Map<String, String> errors = new HashMap<>();

    public void addError(String field, String message) {
        this.errors.put(field, message);
    }
}
