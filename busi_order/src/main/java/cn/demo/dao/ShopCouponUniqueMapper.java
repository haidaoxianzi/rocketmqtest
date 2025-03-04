package cn.demo.dao;

import cn.demo.model.ShopCouponUnique;

public interface ShopCouponUniqueMapper {
    int insert(ShopCouponUnique record);

    int insertSelective(ShopCouponUnique record);
}