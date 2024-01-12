//package com.example.ikframbe.model.database;
//
//import javax.persistence.*;
//import java.util.Objects;
//
//import org.hibernate.annotations.GenericGenerator;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//import java.util.Set;
//
//@Entity
//@Table(name="carts")
//public class Cart {
//    @Id
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
//    private String id;
//
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    @OnDelete(action = OnDeleteAction.CASCADE)
//    private User user;
//
//
//
//    public Cart() {}
//
//    public Cart(User user) {
//        this.user = user;
//    }
//}