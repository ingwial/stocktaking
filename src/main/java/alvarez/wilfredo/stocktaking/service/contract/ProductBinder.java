package alvarez.wilfredo.stocktaking.service.contract;

import alvarez.wilfredo.stocktaking.datasource.entity.Product;
import alvarez.wilfredo.stocktaking.service.contract.to.ProductTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductBinder {
    ProductBinder PRODUCT_BINDER = Mappers.getMapper(ProductBinder.class);

    ProductTO bind(Product product);

    Product bind(ProductTO productTO);

    default Product bind(ProductTO source, Product target) {
        target.setBarcode(source.getBarcode());
        target.setItem(source.getItem());
        target.setCategory(source.getCategory());
        target.setDiscount(source.getDiscount());
        target.setPrice(source.getPrice());
        target.setAvailable(source.getAvailable());
        return target;
    }
}
