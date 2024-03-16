package system2.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import system2.dto.DiscountRequest;
import system2.dto.DiscountResponse;
import system2.mapper.DiscountMapper;
import system2.model.Discount;
import system2.repository.DiscountRepository;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountMapper discountMapper;

    private final DiscountRepository discountRepository;

    private final KafkaTemplate<String, DiscountResponse> kafkaTemplate;


    public DiscountServiceImpl(DiscountMapper discountMapper, DiscountRepository discountRepository,
                               KafkaTemplate<String, DiscountResponse> kafkaTemplate) {
        this.discountMapper = discountMapper;
        this.discountRepository = discountRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public DiscountResponse create(DiscountRequest disCountRequest) {
        Discount discount = discountMapper.discountRequestToDiscount(disCountRequest);
        discountRepository.save(discount);

        DiscountResponse discountResponse = discountMapper.discountToDiscountResponse(discount);
        kafkaTemplate.send("discount-0.0",discountResponse);

        return discountResponse;
    }

    @Override
    public DiscountResponse get(Long id) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount != null) {
            return discountMapper.discountToDiscountResponse(discount);
        }
        return null;
    }

    @Override
    public DiscountResponse update(DiscountRequest discountRequest, Long id) {
        Discount discount = discountRepository.findById(id).orElse(null);
        if (discount != null) {
            discount.setDiscountAmount(discountRequest.getDiscountAmount());
            discount.setDisCountDescription(discountRequest.getDisCountDescription());
            discount.setDisCountType(discountRequest.getDisCountType());
            discountRepository.save(discount);

            DiscountResponse discountResponse = discountMapper.discountToDiscountResponse(discount);
            kafkaTemplate.send("discount-0.0",discountResponse);

            return discountResponse;
        }
        return null;
    }


    @Override
    public List<DiscountResponse> getDiscounts() {
        return discountRepository.findAll().stream().map(discountMapper::discountToDiscountResponse).toList();
    }
}
