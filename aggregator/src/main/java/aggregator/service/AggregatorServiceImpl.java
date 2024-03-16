package aggregator.service;

import aggregator.model.Aggregator;
import aggregator.repository.AggregatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AggregatorServiceImpl implements AggregatorService {
    private final AggregatorRepository aggregatorRepository;


    @Override
    public List<Aggregator> getProductsAndDiscounts() {
        return aggregatorRepository.findAll();
    }
}
