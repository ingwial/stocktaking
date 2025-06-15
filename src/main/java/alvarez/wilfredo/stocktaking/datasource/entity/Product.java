package alvarez.wilfredo.stocktaking.datasource.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table
@Entity
public class Product implements Comparable<Product> {
    @Id
    private String barcode;
    private String item;
    private String category;
    private Integer price;
    private Integer discount;
    private Character available;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Character getAvailable() {
        return available;
    }

    public void setAvailable(Character available) {
        this.available = available;
    }

    @Override
    public int compareTo(Product o) {
        return this.getPrice().compareTo(o.getPrice());
    }
}
