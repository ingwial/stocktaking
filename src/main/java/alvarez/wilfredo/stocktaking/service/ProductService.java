package alvarez.wilfredo.stocktaking.service;

import alvarez.wilfredo.stocktaking.service.contract.to.ProductTO;

import java.util.List;

public interface ProductService {
    List<ProductTO> getAll();
    ProductTO getById(String id) throws IllegalArgumentException;
    ProductTO create(ProductTO productTO);
    ProductTO update(String id, ProductTO productTO) throws IllegalArgumentException;
    void delete(String id) throws IllegalArgumentException;
    List<ProductTO> getFilteredProducts(Integer initialRange, Integer finalRange);
    List<ProductTO> getFilteredProductsByPrice();
}
