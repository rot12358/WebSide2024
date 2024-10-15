package binh.dev.data.model;

import binh.dev.data.dao.DatabaseDao;

public class OrderItem {

    private int id;
    private int quantity;
    private double price;
    private int orderId;
    private int productId;
    private String orderStatus;

    public OrderItem(int quantity, double price, int orderId, int productId) {
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
    }

    public OrderItem(int quantity, double price, int productId, String orderStatus) {
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.orderStatus = orderStatus;
    }

    public OrderItem(int id, int quantity, double price, int orderId, int productId) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.orderId = orderId;
        this.productId = productId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Product getProduct() {
        return DatabaseDao.getInstance().getProductDao().find(this.productId);
    }

    public Order getOrder() {
        return DatabaseDao.getInstance().getOrderDao().find(this.orderId);
    }
}
