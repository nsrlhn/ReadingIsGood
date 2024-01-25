package ilhan.ensar.ReadingIsGood.controller.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CustomerPostRequest implements Serializable {

    @NotNull
    @Schema(example = "Ensar")
    private String firstName;

    private String middleName;

    @NotNull
    @Schema(example = "ILHAN")
    private String familyName;

    @NotNull
    @Schema(example = "password")
    private String password;

    @NotNull
    @Schema(example = "exampl@gmail.com")
    private String mail;
}
