package edu.imdadia.qurbani.Entity;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Table(name = "dataManagement", schema = "tailor")
public class DataManagementEntity {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DataManagement_SEQ_GEN")
    @SequenceGenerator(name = "DataManagement_SEQ_GEN", sequenceName = "DataManagement_ID_SEQ")
    @Id
    @Column(name = "DataManagement_id")
    private Integer dataManagementId;

    @Column(name = "choice_animal")
    private String choiceAnimalBox;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MOBILE_NO")
    private String mobileNo;
//
    @Column(name = "DELIVERY_DATE")
    private LocalDate deliveryDate;
//
    @Column(name = "paidItem")
    private Integer paidItem;


    @Column(name = "unpaidItem")
    private Integer unpaidItem;

}
