package ilhan.ensar.ReadingIsGood.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
public class ErrorResponse implements Serializable {

    private int status;
    private String error;
    private String message;
    private long timestamp;

    public static ErrorResponse from(BadRequestException ex, HttpStatus status) {
        ErrorResponse response = new ErrorResponse();
        response.setStatus(status.value());
        response.setError(ex.getError());
        response.setMessage(ex.getMessage());
        response.setTimestamp(System.currentTimeMillis());
        return response;
    }
}