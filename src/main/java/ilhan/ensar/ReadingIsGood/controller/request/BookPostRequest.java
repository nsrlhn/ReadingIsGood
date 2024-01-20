package ilhan.ensar.ReadingIsGood.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class BookPostRequest implements Serializable {

    @NotNull
    @Schema(example = "The Mathematical Principles of Natural Philosophy")
    private String name;

    @NotNull
    @Schema(example = "1000")
    private Integer availableAmount;

    @NotNull
    @Schema(example = "149.99")
    private BigDecimal price;
}
