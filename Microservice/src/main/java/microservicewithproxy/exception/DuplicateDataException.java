package microservicewithproxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.CONFLICT, reason="Cannot duplicate unique information")
public class DuplicateDataException extends RuntimeException  {
    public DuplicateDataException(String message) {
        super(message);
    }
}
