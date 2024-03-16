package system2.service;

import system2.dto.DiscountRequest;
import system2.dto.DiscountResponse;

import java.util.List;

public interface DiscountService {
    DiscountResponse create(DiscountRequest disCountRequest);

    DiscountResponse get(Long id);

    DiscountResponse update(DiscountRequest discountRequest, Long id);

    List<DiscountResponse> getDiscounts();
}
