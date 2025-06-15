package alvarez.wilfredo.stocktaking.rest;

import alvarez.wilfredo.stocktaking.service.ProductService;
import alvarez.wilfredo.stocktaking.service.contract.to.ProductTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<ProductTO>> getProducts() {
        return ResponseEntity.ok(this.productService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductTO> getProduct(@PathVariable String id) {
        try {
            return ResponseEntity.ok(this.productService.getById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProductTO> createProduct(@RequestBody ProductTO productTO) {
        return ResponseEntity.ok(this.productService.create(productTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductTO> updateProduct(@PathVariable String id, @RequestBody ProductTO productTO) {
        try {
            productTO = this.productService.update(id, productTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable String id) {
        try {
            this.productService.delete(id);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/filter/price/{initial_range}/{final_range}")
    public ResponseEntity<List<ProductTO>> getFilteredProducts(@PathVariable("initial_range") Integer initialRange,
                                                               @PathVariable("final_range") Integer finalRange
    ) {
        return ResponseEntity.ok(this.productService.getFilteredProducts(initialRange, finalRange));
    }

    @GetMapping("/sort/price")
    public ResponseEntity<List<ProductTO>> getFilteredProductsByPrice() {
        return ResponseEntity.ok(this.productService.getFilteredProductsByPrice());
    }
}
