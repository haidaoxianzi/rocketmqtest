package cn.demo.dao;

import cn.demo.model.ShopCoupon;

public interface ShopCouponMapper {
    int deleteByPrimaryKey(Long couponId);

    int insert(ShopCoupon record);

    int insertSelective(ShopCoupon record);

    ShopCoupon selectByPrimaryKey(Long couponId);

    int updateByPrimaryKeySelective(ShopCoupon record);

    int updateByPrimaryKey(ShopCoupon record);
}