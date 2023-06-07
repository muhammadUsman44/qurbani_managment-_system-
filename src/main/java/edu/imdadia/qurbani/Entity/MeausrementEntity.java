package edu.imdadia.qurbani.Entity;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "measurements", schema = "tailor")
public class MeausrementEntity {

    @Id
    @Column(name = "MEAUSREMENT_ID")
    private Integer meausrementId;


    @Column(name = "CUSTOMER_FIRST_NAME")
    private String firstName;

    @Column(name = "CUSTOMER_LAST_NAME")
    private String lastName;

    @Column(name = "CUSTOMER_GENDER")
    private String gender;

    @Column(name = "CUSTOMER_MOBILE_NO")
    private String mobileNo;

    @Column(name = "CUSTOMER_CNIC_NO")
    private String cnicNo;

    @Column(name = "QAMIZ_LENGTH")
    private Double QamizLength;

    @Column(name = "SLEEVS")
    private Double sleevs;

    @Column(name = "SHOULDER")
    private Double shoulder;

    @Column(name = "NECK")
    private Double nack;

    @Column(name = "CHEST")
    private Double chest;

    @Column(name = "WAIST")
    private Double waist;

    @Column(name = "QAMIZ_BOTTOM")
    private Double QamizBottom;

    @Column(name = "SHALWAR_LENGTH")
    private Double shalwarLength;

    @Column(name = "SHALWAR_BOTTOM")
    private Double shalwarBottom;

    @Column(name = "TROUSER_LENGTH")
    private Double trouserLength;

    @Column(name = "TROUSER_BOTTOM")
    private Double trouserBottom;

    @Column(name = "TROUSER_HIP")
    private Double trouserHip;

    @Column(name = "TROUSER_THAI")
    private Double trouserThai;

    @Column(name = "FRONT_POCKET")
    private String frontPocket;

    @Column(name = "POCKET_TYPE")
    private String pocketType;

    @Column(name = "SIDE_PCKET")
    private String sidePocket;

    @Column(name = "CALF_TYPE")
    private String calfType;

    @Column(name = "CALF_SIFE")
    private Double calfSize;

    @Column(name = "ZIP")
    private String zip;

    @Column(name = "QAMIZ_BOTTOM_TYPE")
    private String qamizBottomType;

    @Column(name = "COLLAR_TYPE")
    private String collarType;

    @Column(name = "QAMIZ_FRONT_STRIP")
    private Double qamizFrontStrip;

    @Column(name = "QAMIZ_FRONT_STRIP_TYPE")
    private String qamizFrontStripType;

    @Column(name = "THREAD")
    private String thread;


}
