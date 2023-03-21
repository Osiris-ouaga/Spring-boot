package com.course.client.beans;

public class CartItemBean {

    private Long id;

    private Long productId;

    private Integer quantity;

    private String name;

    private String description;

    private String illustration;

    private Double price;

    public CartItemBean() {
    }
    public CartItemBean(Long productId, Integer quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItemBean(Long id, Long productId, Integer quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItemBean(Long productId, Integer quantity, ProductBean product) {
        this.productId = productId;
        this.quantity = quantity;
        this.name = product.getName();
        this.description = product.getDescription();
        this.illustration = product.getIllustration();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getIllustration() {
        return illustration;
    }

    public Double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIllustration(String illustration) {
        this.illustration = illustration;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cart Item :"+id+":"+productId+":"+quantity;
    }
}
