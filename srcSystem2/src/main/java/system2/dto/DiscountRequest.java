package system2.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import system2.model.DiscountType;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountRequest {
    @NotNull
    private DiscountType disCountType;

    private String disCountDescription;
    @NotNull
    private BigDecimal discountAmount;
}
