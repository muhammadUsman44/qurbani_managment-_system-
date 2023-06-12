package edu.imdadia.qurbani.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Users", schema = "qms")
public class UserEntity {


    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SEQ_GEN")
    @SequenceGenerator(name = "USERS_SEQ_GEN", sequenceName = "USERS_ID_SEQ")
    @Id
    @Column(name = "USER_ID")
    private Integer userid;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "MOBILE_NO")
    private String mobileNo;


    @Column(name = "USER_PASSWORD")
    private String userPassword;

    public UserEntity() {

    }


}
