package com.example.order.domain;


import javax.persistence.*;
import java.util.List;

@Entity
public class Orderr {
    @Id
    @GeneratedValue
    private Long id;

    private Long cartId;

    private Double totalPrice;
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> products;

    public Orderr(Long id, Double totalPrice) {
        this.id = id;
        this.totalPrice = totalPrice;
    }

    public Orderr() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setProducts(List<OrderItem> products) {
        this.products = products;
    }

    public List<OrderItem> getProducts() {
        return products;
    }

    public void addProduct(OrderItem product) {
        this.products.add(product);
    }

}
