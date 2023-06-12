package edu.imdadia.qurbani.Entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "dataManagement", schema = "qms")
public class DataManagementEntity {

    @Column(name = "choice_animal")
    private String choiceAnimalBox;
    @Id
    @Column(name = "CUSTOMER_FIRST_NAME")
    private String firstName;

    @Column(name = "CUSTOMER_LAST_NAME")
    private String lastName;

    @Column(name = "CUSTOMER_MOBILE_NO")
    private String mobileNo;

    @Column(name = "DELIVERY_DATE")
    private LocalDate deliveryDate;

    @Column(name = "paidItem")
    private Integer paidItem;


    @Column(name = "unpaidItem")
    private Integer unpaidItem;


}
