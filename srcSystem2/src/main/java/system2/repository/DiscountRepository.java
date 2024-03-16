package system2.repository;

import org.springframework.data.repository.CrudRepository;
import system2.model.Discount;

import java.util.List;

public interface DiscountRepository extends CrudRepository<Discount, Long> {
    List<Discount> findAll();
}
