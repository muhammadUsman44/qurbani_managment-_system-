package edu.imdadia.qurbani.Entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "CowData", schema = "tailor")
public class CowDataEntity {

    @Id
    @Column(name = "ORDER_ID")
    private Integer orderId;


    @Column(name = "COW_NUMBER")
    private String cowNumberField;

    @Column(name = "COW_PRICE")
    private Integer CowPrice;

    @Column(name = "PER_HEAD_PRICE")
    private Integer perHeadPrice;

}
