package aggregator.listener;

import aggregator.dto.DiscountResponse;
import aggregator.dto.ProductResponse;
import aggregator.mapper.AggregatorMapper;
import aggregator.model.Aggregator;
import aggregator.repository.AggregatorRepository;
import lombok.extern.slf4j.Slf4j;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class SourceListener {

    private final AggregatorMapper aggregatorMapper;
    private final AggregatorRepository aggregatorRepository;

    public SourceListener(AggregatorMapper aggregatorMapper, AggregatorRepository aggregatorRepository) {
        this.aggregatorMapper = aggregatorMapper;
        this.aggregatorRepository = aggregatorRepository;
    }


    @KafkaListener(topics = "product-0.0", properties = {"spring.json.value.default.type=aggregator.dto.ProductResponse"})
    public void listen(ProductResponse productResponse) {
        Optional<Aggregator> aggregatorOptional = aggregatorRepository.findByProductId(productResponse.getId());
        Aggregator aggregator;
        if(aggregatorOptional.isEmpty()){
            aggregator = aggregatorMapper.productToAggregatorMapper(productResponse);
        }
        else{
            aggregator = aggregatorOptional.get();
            aggregator.setProductId(productResponse.getId());
            aggregator.setProductDescription(productResponse.getDescription());
            aggregator.setProductTitle(productResponse.getTitle());
            aggregator.setProductPrice(productResponse.getProductPrice());
            aggregator.setDiscountId(productResponse.getDiscountId());
            aggregator.setProductUpdatedDate(String.valueOf(productResponse.getUpdatedDate()));
        }

        aggregatorRepository.save(aggregator);

        log.info("Aggregator message saved successfully in couchbase, Source System: Product, Final Message:{}", aggregator);
    }

    @KafkaListener(topics = "discount-0.0", properties = {"spring.json.value.default.type=aggregator.dto.DiscountResponse"})
    public void listen(DiscountResponse discountResponse) {
        List<Aggregator> aggregatorList = aggregatorRepository.findByDiscountId(discountResponse.getId());

        for(Aggregator aggregator: aggregatorList){
            aggregator.setDiscountAmount(discountResponse.getDiscountAmount());
            aggregator.setDisCountType(discountResponse.getDisCountType());
            aggregator.setDiscountUpdatedTime(String.valueOf(discountResponse.getUpdatedTime()));
            aggregator.setDisCountDescription(discountResponse.getDisCountDescription());
            aggregatorRepository.save(aggregator);
        }
        log.info("Aggregator message saved successfully in couchbase, Source System: Discount, Final Message:{}", discountResponse);
    }
}
