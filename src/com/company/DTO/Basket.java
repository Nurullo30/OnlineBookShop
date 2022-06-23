package com.company.DTO;
import com.company.servlets.BasketServlet;
import java.util.List;

public class Basket {
    int quantity;
    String productId;

    public Basket(){

    }

    public Basket(String productId, int quantity) {
        this.quantity = quantity;
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }
}
