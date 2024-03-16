package system2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import system2.model.DiscountType;

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
