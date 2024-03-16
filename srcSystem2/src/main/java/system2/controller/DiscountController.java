package system2.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system2.dto.DiscountRequest;
import system2.dto.DiscountResponse;
import system2.service.DiscountService;

import java.util.List;

@RestController
@RequestMapping("/discounts")
@RequiredArgsConstructor
public class DiscountController {
    private final DiscountService discountService;

    @PostMapping("")
    public ResponseEntity<DiscountResponse> create(@RequestBody DiscountRequest discountRequest){
        DiscountResponse disCountResponse = discountService.create(discountRequest);
        return new ResponseEntity<>(disCountResponse, HttpStatus.CREATED);
    }

    @GetMapping("{discountId}")
    private ResponseEntity<?> getDiscount(@PathVariable("discountId") Long id) {
        DiscountResponse discountResponse = discountService.get(id);
        if(discountResponse != null){
            return new ResponseEntity<>(discountResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Discount not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("{discountId}")
    private ResponseEntity<?> updateDiscount(@RequestBody @Valid DiscountRequest discountRequest,
                                            @PathVariable("discountId") Long id) {
        DiscountResponse discountResponse = discountService.update(discountRequest, id);
        if(discountResponse != null){
            return new ResponseEntity<>(discountResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Discount not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{discountId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteDiscount(@PathVariable("discountId") Long id) {
        discountService.delete(id);
    }

    @GetMapping("")
    private ResponseEntity<List<DiscountResponse>> getAllDiscount() {
        return new ResponseEntity<>(discountService.getDiscounts(), HttpStatus.OK);
    }

}
