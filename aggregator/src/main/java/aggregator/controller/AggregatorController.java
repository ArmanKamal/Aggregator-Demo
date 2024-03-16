package aggregator.controller;

import aggregator.model.Aggregator;
import aggregator.service.AggregatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AggregatorController {

    private final AggregatorService aggregatorService;

    @RequestMapping("/aggregators")
    public ResponseEntity<?> aggreagtorList(){
        List<Aggregator> aggregatorList = aggregatorService.getProductsAndDiscounts();
        return new ResponseEntity<>(aggregatorList, HttpStatus.OK);
    }

}
