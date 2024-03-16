package system1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import system1.model.Product;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {
    List<Product> findAll();
}
