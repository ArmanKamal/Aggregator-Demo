package system2.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import system2.dto.DiscountRequest;
import system2.dto.DiscountResponse;
import system2.model.Discount;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DiscountMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "createdDate",ignore = true)
    @Mapping(target = "updatedTime",ignore = true)
    Discount discountRequestToDiscount(DiscountRequest discountRequest);

    DiscountResponse discountToDiscountResponse(Discount discount);
}
