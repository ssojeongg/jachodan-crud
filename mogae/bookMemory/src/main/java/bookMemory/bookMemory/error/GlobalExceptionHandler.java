package bookMemory.bookMemory.error;

import bookMemory.bookMemory.error.exception.BusinessException;
import bookMemory.bookMemory.error.response.ErrorResponse;
import bookMemory.bookMemory.error.response.ValidationErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        ErrorCode errorCode = ErrorCode.BAD_REQUEST;
        log.error("[{}] {}", errorCode.getCode(), errorCode.getMessage(), ex);
        ValidationErrorResponse errors = new ValidationErrorResponse(errorCode);
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.addError(error.getField(), error.getDefaultMessage())
        );
        ex.getBindingResult().getGlobalErrors().forEach(error ->
                errors.addError(error.getObjectName(), error.getDefaultMessage())
        );
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(errors);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String code = errorCode.getCode();
        String message = errorCode.getMessage();
        log.error("[{}] {}", code, message, ex);
        ErrorResponse error = new ErrorResponse(code, message);
        return ResponseEntity
                .status(errorCode.getStatus())
                .body(error);
    }
}
