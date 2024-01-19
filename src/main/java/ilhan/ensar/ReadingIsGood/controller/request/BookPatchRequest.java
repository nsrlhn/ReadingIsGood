package ilhan.ensar.ReadingIsGood.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class BookPatchRequest implements Serializable {

    @NotNull
    @Schema(example = "300")
    private Integer addAmount;
}
