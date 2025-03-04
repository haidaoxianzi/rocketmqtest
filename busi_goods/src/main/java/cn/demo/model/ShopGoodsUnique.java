package cn.demo.model;

public class ShopGoodsUnique {
    private Long orderId;

    public ShopGoodsUnique(Long orderId) {
        this.orderId = orderId;
    }

    public ShopGoodsUnique() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}