package system1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;

    private String title;

    private String description;

    private BigDecimal productPrice;

    private Long discountId;

    @Column(updatable = false, name = "created_at_utc")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "updated_at_utc")
    @UpdateTimestamp
    private LocalDateTime updatedDate;

}
