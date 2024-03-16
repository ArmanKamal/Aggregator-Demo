package system1.service;

import system1.dto.ProductRequest;
import system1.dto.ProductResponse;
import system1.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse create(ProductRequest productRequest);

    List<ProductResponse> getProducts();

    ProductResponse get(UUID id);

    ProductResponse update(ProductRequest productRequest, UUID id);

}
