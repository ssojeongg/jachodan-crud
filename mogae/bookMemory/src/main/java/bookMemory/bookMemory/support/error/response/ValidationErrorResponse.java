package bookMemory.bookMemory.support.error.response;

import bookMemory.bookMemory.support.error.ErrorCode;
import lombok.Getter;

import java.util.*;

@Getter
public class ValidationErrorResponse extends ErrorResponse{
    private final Map<String, List<String>> errors = new HashMap<>();

    public ValidationErrorResponse(ErrorCode errorCode) {
        super(errorCode.getCode(), errorCode.getMessage());
    }

    public void addError(String field, String message) {
        errors.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
    }

    public Map<String, List<String>> getErrors() {
        return Collections.unmodifiableMap(errors);
    }
}
