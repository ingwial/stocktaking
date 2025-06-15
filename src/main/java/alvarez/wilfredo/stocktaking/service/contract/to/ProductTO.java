package alvarez.wilfredo.stocktaking.service.contract.to;

public class ProductTO {
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
}
