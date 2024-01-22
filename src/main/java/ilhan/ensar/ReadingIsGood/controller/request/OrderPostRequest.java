package ilhan.ensar.ReadingIsGood.controller.request;

import ilhan.ensar.ReadingIsGood.model.Order;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class OrderPostRequest implements Serializable {

    @NotNull
    @Schema(example = "1")
    private Long customerId;

    @NotNull
    @Schema(example = "COMPLETED")
    private Order.Status status;

    @NotNull
    @Valid
    private List<OrderItemPostRequest> orderItemPostRequestList;
}
