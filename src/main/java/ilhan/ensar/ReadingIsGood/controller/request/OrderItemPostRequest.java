package ilhan.ensar.ReadingIsGood.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderItemPostRequest implements Serializable {

    @NotNull
    @Schema(example = "1")
    private Long bookId;

    @NotNull
    @Schema(example = "1")
    private Integer amount;
}
