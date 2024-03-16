package aggregator.dto;

import aggregator.model.DiscountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscountResponse {
    private Long id;
    private DiscountType disCountType;
    private String disCountDescription;
    private BigDecimal discountAmount;
    private LocalDateTime updatedTime;

}
