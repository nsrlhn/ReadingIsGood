package ilhan.ensar.ReadingIsGood.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.io.Serializable;

@Data
public class CustomerPostRequest implements Serializable {

    @NotNull
    @Schema(example = "Ensar")
    private String firstName;

    private String middleName;

    @NotNull
    @Schema(example = "ILHAN")
    private String familyName;
}
