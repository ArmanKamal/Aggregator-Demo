package aggregator.mapper;
import aggregator.dto.DiscountResponse;
import aggregator.dto.ProductResponse;
import aggregator.model.Aggregator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AggregatorMapper {


    @Mapping(target = "productId",source = "id")
    @Mapping(target = "productPrice",source = "productPrice")
    @Mapping(target = "productTitle",source = "title")
    @Mapping(target = "productDescription",source = "description")
    @Mapping(target = "productUpdatedDate",source = "updatedDate")
    @Mapping(target = "discountId",source = "discountId")
    Aggregator productToAggregatorMapper(ProductResponse product);

    @Mapping(target = "discountId",source = "id")
    @Mapping(target = "discountAmount",source = "discountAmount")
    @Mapping(target = "disCountType",source = "disCountType")
    @Mapping(target = "disCountDescription",source = "disCountDescription")
    @Mapping(target = "discountUpdatedTime",source = "updatedTime")
    Aggregator discountToAggregatorMapper(DiscountResponse discountResponse);

//    Product productResponseToProduct(ProductResponse productResponse);
//
//    Discount discountResponseToDiscount(DiscountResponse discountResponse);


}
