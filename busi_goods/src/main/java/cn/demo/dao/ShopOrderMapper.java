package cn.demo.dao;

import cn.demo.model.ShopOrder;

public interface ShopOrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insert(ShopOrder record);

    int insertSelective(ShopOrder record);

    ShopOrder selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(ShopOrder record);

    int updateByPrimaryKey(ShopOrder record);
}