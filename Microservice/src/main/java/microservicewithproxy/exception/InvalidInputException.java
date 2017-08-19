package microservicewithproxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.PRECONDITION_FAILED, reason="Missing mandatory parameter")
public class InvalidInputException extends RuntimeException  {
    public InvalidInputException(String message) {
        super(message);
    }
}