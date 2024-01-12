//package com.example.ikframbe.controller;
//
//import com.example.ikframbe.DAO.CartDAO;
//import com.example.ikframbe.model.database.Cart;
//import com.example.ikframbe.model.database.Order;
//import com.example.ikframbe.model.database.User;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/cart")
//public class CartController {
//    private final CartDAO cartDAO;
//
//    public CartController(CartDAO cartDAO) {
//        this.cartDAO = cartDAO;
//    }
//
//    @GetMapping
//    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
//    public ResponseEntity<List<Order>> getCarts(@AuthenticationPrincipal User user) {
//        return switch (user.getRole().getName()) {
//            case "USER" -> ResponseEntity.ok().body(CartDAO.getOrdersByUserId(user.getId()));
//            case "ADMIN" -> ResponseEntity.ok().body(CartDAO.getAll());
//            default -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        };
//    }
//}
