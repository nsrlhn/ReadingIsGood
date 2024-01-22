package ilhan.ensar.ReadingIsGood.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends BadRequestException {

    private final String error = "Not Found";

    public NotFoundException(String message) {
        super(message);
    }

}
