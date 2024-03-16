package system1.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import system1.dto.ProductRequest;
import system1.dto.ProductResponse;
import system1.model.Product;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
@Component
public interface ProductMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "updatedDate", ignore = true)
    Product productRequestToProduct(ProductRequest productRequest);

    ProductResponse productToProductResponse(Product product);
}
