package edu.imdadia.tailor.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "orders", schema = "tailor")
public class OrderEntity {

    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;


    @Column(name = "PRODUCT_NAME")
    private String productName;

    @Column(name = "PRODUCT_PRICE")
    private Integer productPrice;

    @Column(name = "PRODUCT_QUANTITY")
    private Integer productQuantity;

    @Column(name = "TOTAL_PRICE")
    private Integer totalPrice;

    @Column(name = "DELIVERY_DATE")
    private LocalDate deliveryDate;
}
