package smart.cart.client.shared;

import androidx.annotation.NonNull;

public class Item {
    private String itemId;
    private String name;
    private Number price;
    private Number quantity;

    public Item(String cartId, String name, Number price, Number quantity) {
        this.itemId = cartId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public Item() {
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String cartId) {
        this.itemId = cartId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Number getPrice() {
        return price;
    }

    public void setPrice(Number price) {
        this.price = price;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }

    @NonNull
    @Override
    public String toString() {
        return "Item{cartId=" + itemId + ", name=" + name + ", price=" + price + ", quantity=" + quantity + "}";
    }
}
