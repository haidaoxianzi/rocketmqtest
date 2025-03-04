package cn.demo.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.client.producer.TransactionSendResult;
import org.apache.rocketmq.common.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Component
public class TransactionProducer {
    
    private String producerGroup = "order_trans_group";

    // 事务消息
    private TransactionMQProducer producer;

    //用于执行本地事务和事务状态回查的监听器
    @Autowired
    OrderTransactionListener orderTransactionListener;
    //执行（定时回查）任务的线程池
    ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60,
            TimeUnit.SECONDS, new ArrayBlockingQueue<>(50));
            
    @PostConstruct
    public void init(){
        producer = new TransactionMQProducer(producerGroup);
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setSendMsgTimeout(Integer.MAX_VALUE);
        producer.setExecutorService(executor);
        producer.setRetryTimesWhenSendFailed(2);//todo 是重试发送次数吗，redis多级缓存咖啡因也看下有没有这个属性
        producer.setTransactionListener(orderTransactionListener);
        System.out.println("order_trans_group init done");
        this.start();
    }
    public TransactionProducer(){
        System.out.println("TransactionProducer 构造函数 done");

    }
    private void start(){
        try {
            this.producer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
    //事务消息发送 
    public TransactionSendResult send(String data, String topic) throws MQClientException {
        Message message = new Message(topic,data.getBytes());
        return this.producer.sendMessageInTransaction(message, null);
    }
}