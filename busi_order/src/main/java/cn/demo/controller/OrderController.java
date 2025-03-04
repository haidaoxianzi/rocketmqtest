package cn.demo.controller;

import cn.demo.config.TransactionProducer;
import cn.demo.model.ShopOrder;
import cn.demo.service.OrderServiceImpl;
import com.google.gson.Gson;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;

/**
 * 类说明：订单相关的类
 */
@RestController
public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private static final String SUCCESS = "success";
    private static final String FAILUER = "failure";

    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    TransactionProducer producer;


    //  http://localhost:8080/submitOrder?userId=1&goodsId=13&goodsNumber=1&couponId=1
    //提交订单
    @RequestMapping("/submitOrder")
    public String submitOrder(@RequestParam("userId")long userId,@RequestParam("goodsId")long goodsId,@RequestParam("goodsNumber")int goodsNumber,@RequestParam("couponId")long couponId){
        long orderid ;
        try {
            //check() 略过
            //确认订单
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setUserId(userId);
            shopOrder.setGoodsId(goodsId);
            shopOrder.setGoodsNumber(goodsNumber);
            shopOrder.setCouponId(couponId);
            shopOrder.setOrderStatus(0);
            shopOrder.setPayStatus(1);
            shopOrder.setShippingStatus(0);
            shopOrder.setAddTime(new Date());
            orderid =orderService.submitOrder(shopOrder);
        } catch (Exception e) {
            e.printStackTrace();
            return FAILUER;
        }
        if(orderid>0){
            return SUCCESS+":"+orderid;
        }else{
            logger.error("提交订单失败：[" + orderid + "]");
            return FAILUER;
        }
    }

    @RequestMapping("/trans-order")
    public String TransOrder(@RequestParam("userId")long userId, @RequestParam("goodsId")long goodsId, @RequestParam("goodsNumber")int goodsNumber, @RequestParam("couponId")long couponId) {
        long orderid;
        try {
            ShopOrder shopOrder = new ShopOrder();
            shopOrder.setUserId(userId);
            shopOrder.setGoodsId(goodsId);
            shopOrder.setGoodsNumber(goodsNumber);
            shopOrder.setCouponId(couponId);
            shopOrder.setOrderStatus(0);
            shopOrder.setPayStatus(1);
            shopOrder.setShippingStatus(0);
            shopOrder.setAddTime(new Date());

            //TODO 使用Gson序列化
            Gson gson = new Gson();
            String txtMsg = gson.toJson(shopOrder);
            //发送半事务消息
            SendResult sendResult =producer.send(txtMsg,"trans-order");
            if(sendResult.getSendStatus() == SendStatus.SEND_OK){
                return  SUCCESS;
            }else{
                logger.error("MQ发送消息失败：[" + shopOrder.getOrderId() + "]");
                return  FAILUER;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return FAILUER;
        }
    }
//生产者先发送半事务消息

}
