package system1.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import system1.dto.ProductRequest;
import system1.dto.ProductResponse;
import system1.exception.ProductServiceException;
import system1.mapper.ProductMapper;
import system1.model.Product;
import system1.repository.ProductRepository;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    private final ProductMapper productMapper;

    private final KafkaTemplate<String, ProductResponse> kafkaTemplate;


    public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper,
                              KafkaTemplate<String, ProductResponse> kafkaTemplate) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public ProductResponse create(ProductRequest productRequest) {
        try {
            Product product = productMapper.productRequestToProduct(productRequest);
            productRepository.save(product);
            log.info("Saved product successfully {}", product);

            ProductResponse productResponse = productMapper.productToProductResponse(product);
            kafkaTemplate.send("product-0.0", productResponse);
            log.info("Product Message send successfully{} to topic product-0.0", productResponse);

            return productResponse;
        } catch (Exception ex) {
            throw new ProductServiceException(ex.getMessage(), ex);
        }
    }

    @Override
    public List<ProductResponse> getProducts() {
        return productRepository.findAll().stream().map(productMapper::productToProductResponse).toList();
    }

    @Override
    public ProductResponse get(UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) return productMapper.productToProductResponse(product);
        return null;
    }

    @Override
    public ProductResponse update(ProductRequest productRequest, UUID id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product != null) {
            product.setProductPrice(productRequest.getProductPrice());
            product.setTitle(productRequest.getTitle());
            product.setDescription(productRequest.getDescription());

            productRepository.save(product);
            log.info("Updated product successfully {}", product);

            ProductResponse productResponse = productMapper.productToProductResponse(product);
            kafkaTemplate.send("product-0.0", productResponse);
            log.info("Product Message send successfully{} to topic product-0.0", productResponse);

            return productResponse;
        }
        return null;
    }

}
