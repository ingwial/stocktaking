package alvarez.wilfredo.stocktaking.service.impl;

import alvarez.wilfredo.stocktaking.datasource.ProductRepository;
import alvarez.wilfredo.stocktaking.datasource.entity.Product;
import alvarez.wilfredo.stocktaking.service.ProductService;
import alvarez.wilfredo.stocktaking.service.contract.to.ProductTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static alvarez.wilfredo.stocktaking.service.contract.ProductBinder.PRODUCT_BINDER;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductTO> getAll() {
        List<Product> products = this.productRepository.findAll();
        List<ProductTO> productTOList = new ArrayList<>();
        products.forEach(product -> productTOList.add(PRODUCT_BINDER.bind(product)));
        return productTOList;
    }

    @Override
    public ProductTO getById(String id) throws IllegalArgumentException {
        Product product = this.productRepository.findById(id).orElse(null);
        if (Objects.nonNull(product)) {
            return PRODUCT_BINDER.bind(product);
        }
        throw new IllegalArgumentException("Product not found");
    }

    @Override
    public ProductTO create(ProductTO productTO) {
        if (Objects.isNull(productTO.getBarcode())) {
            productTO.setBarcode(UUID.randomUUID().toString());
        }
        Product product = this.productRepository.save(PRODUCT_BINDER.bind(productTO));
        return PRODUCT_BINDER.bind(product);
    }

    @Override
    public ProductTO update(String id, ProductTO productTO) throws IllegalArgumentException {
        productTO.setBarcode(id);
        Product productFound = this.productRepository.findById(id).orElse(null);
        if (Objects.nonNull(productFound)) {
            Product updatedProduct = PRODUCT_BINDER.bind(productTO, productFound);
            this.productRepository.save(updatedProduct);
            return productTO;
        }
        throw new IllegalArgumentException("Product not found");
    }

    @Override
    public void delete(String id) throws IllegalArgumentException {
        Product productFound = this.productRepository.findById(id).orElse(null);
        if (Objects.nonNull(productFound)) {
            this.productRepository.delete(productFound);
            return;
        }
        throw new IllegalArgumentException("Product not found");
    }

    @Override
    public List<ProductTO> getFilteredProducts(Integer initialRange, Integer finalRange) {
        return this.productRepository.findAll().stream()
                .filter(product -> product.getPrice() >= initialRange && product.getPrice() <= finalRange)
                .map(PRODUCT_BINDER::bind).toList();
    }

    @Override
    public List<ProductTO> getFilteredProductsByPrice() {
        return this.productRepository.findAll().stream()
                .sorted(Comparator.comparingInt(Product::getPrice))
                .map(PRODUCT_BINDER::bind).toList();
    }
}
