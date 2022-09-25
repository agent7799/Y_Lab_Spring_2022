package com.edu.ulab.app.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
//@Table(name = "perons")
public class UserEntity {

//    public PersonEntity() {
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column
    private String fullName;

//    @Column
    private String title;

//    @Column
    private int age;

}
