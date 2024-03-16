package aggregator.repository;

import aggregator.model.Aggregator;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Repository
public interface AggregatorRepository extends CrudRepository<Aggregator, UUID> {
    List<Aggregator> findAll();
    Optional<Aggregator> findByProductId(UUID productId);
    List<Aggregator> findByDiscountId(Long discountId);
}
