package demo.jachodan.crud;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "entity not found")
public class err404Exception extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public err404Exception(String message) {
        super(message);
    }
}
