package ilhan.ensar.ReadingIsGood.exception;

import lombok.Getter;

@Getter
public class BusinessLogicException extends BadRequestException {

    private final String error = "Business Logic Error";

    public BusinessLogicException(String message) {
        super(message);
    }

}
