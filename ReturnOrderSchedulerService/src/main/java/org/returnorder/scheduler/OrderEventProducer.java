package org.returnorder.scheduler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import java.util.Properties;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

public class OrderEventProducer {
    private KafkaProducer<String, String> producer;
    private Connection connection;
    private ObjectMapper objectMapper= new ObjectMapper(); // Jackson's ObjectMapper for JSON serialization

    public OrderEventProducer() {
        // Initialize Kafka Producer
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
        
        // Initialize database connection
        try {
            connection = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/ordermanagementsystem",
                            "root", "RVSram@35034");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        // Schedule the periodic task
        Timer timer = new Timer();
     //   timer.schedule(new FetchAndSendDataTask(), 0, 3600 * 1000);  // Schedule the task to run every hour
        timer.schedule(new FetchAndSendDataTask(), 0, 60 * 1000);  // Schedule the task to run every minute

    }

    public void sendOrderData(String orderData) {
        producer.send(new ProducerRecord<>("returnOrdersTopic", orderData));
        producer.flush();
    }

    class FetchAndSendDataTask extends TimerTask {
        public void run() {
            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery("SELECT * FROM ReturnOrders WHERE" +
                        " refundmode IS NULL " +
                        "OR refundmode = '' ") ;
                while (rs.next()) {
                    // Assuming the data from the ResultSet needs to be converted to a String format for Kafka
                    int orderId = rs.getInt("order_id");
                    int customerId = rs.getInt("customer_id");
                    double productPrice = rs.getDouble("product_price");
                    String returnOrderCondition = rs.getString("return_order_condition");
                    String returnOrderStatus = rs.getString("return_order_status");

                    OrderData orderData = new OrderData
                            (orderId, customerId, productPrice, returnOrderCondition
                            ,returnOrderStatus);
                    orderData.setReturnOrderStatus(returnOrderStatus);
                    String jsonOrderData = convertToJson(orderData);

                    sendOrderData(jsonOrderData);
                    System.out.println("Published to kafka"+ orderData);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    private String convertToJson(OrderData orderData) {
        try {
            return objectMapper.writeValueAsString(orderData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        new OrderEventProducer();
    }
}
