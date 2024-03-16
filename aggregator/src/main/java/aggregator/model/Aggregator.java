package aggregator.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "aggregator",schema = "aggregator")
@NoArgsConstructor
@AllArgsConstructor
public class Aggregator {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID enterpriseId;
    private UUID productId;
    private String productTitle;
    private String productDescription;
    private BigDecimal productPrice;
    private String productUpdatedDate;
    private Long discountId;
    private DiscountType disCountType;
    private String disCountDescription;
    private BigDecimal discountAmount;
    private String discountUpdatedTime;
}
