package cn.demo.dao;

import cn.demo.model.ShopGoodsUnique;

public interface ShopGoodsUniqueMapper {
    int insert(ShopGoodsUnique record);

    int insertSelective(ShopGoodsUnique record);
}