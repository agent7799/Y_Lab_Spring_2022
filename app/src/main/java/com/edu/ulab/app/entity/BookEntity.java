package com.edu.ulab.app.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    @Column(name = "userId")
    private Long userId;

//    @Column
    private String title;

//    @Column
    private String author;

//    @Column
    private long pageCount;
}
