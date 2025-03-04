package cn.demo.model;

public class ShopCouponUnique {
    private Long orderId;

    public ShopCouponUnique(Long orderId) {
        this.orderId = orderId;
    }

    public ShopCouponUnique() {
        super();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}