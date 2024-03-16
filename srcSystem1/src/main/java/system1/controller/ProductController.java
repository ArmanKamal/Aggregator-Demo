package system1.controller;


import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import system1.dto.ProductRequest;
import system1.dto.ProductResponse;
import system1.service.ProductService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("")
    private ResponseEntity<ProductResponse> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        return new ResponseEntity<>(productService.create(productRequest), HttpStatus.CREATED);
    }

    @GetMapping("{productId}")
    private ResponseEntity<?> getProduct(@PathVariable("productId") UUID id) {
        ProductResponse productResponse = productService.get(id);
        if(productResponse != null){
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("{productId}")
    private ResponseEntity<?> updateProduct(@RequestBody @Valid ProductRequest productRequest,
                                                          @PathVariable("productId") UUID id) {
        ProductResponse productResponse = productService.update(productRequest, id);
        if(productResponse != null){
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        }
        return new ResponseEntity<>("Product not found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void deleteProduct(@PathVariable("productId") UUID id) {
        productService.delete(id);
    }

    @GetMapping("")
    private ResponseEntity<List<ProductResponse>> getAllProduct() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.OK);
    }
}
