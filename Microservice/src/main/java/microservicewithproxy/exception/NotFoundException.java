package microservicewithproxy.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Requested resource does not exist")
public class NotFoundException extends RuntimeException  {
    public NotFoundException(String message) {
        super(message);
    }
}
