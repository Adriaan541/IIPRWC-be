package com.example.iprwcbe.controller;

import com.example.iprwcbe.DAO.OrderDAO;
import com.example.iprwcbe.DTOs.OrderConverter;
import com.example.iprwcbe.DTOs.OrderDTO;
import com.example.iprwcbe.model.database.Order;
import com.example.iprwcbe.model.database.OrderProduct;
import com.example.iprwcbe.model.database.Product;
import com.example.iprwcbe.model.database.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    @PersistenceContext
    private EntityManager entityManager;
    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {

        this.orderDAO = orderDAO;
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<List<OrderDTO>> getOrders(@AuthenticationPrincipal User user) {
        List<Order> orders;
        List<OrderDTO> orderDTOs;

        if (user.getRole().getName().equals("USER")) {
            orders = orderDAO.getOrdersByUserId(user.getId());
            orderDTOs = OrderConverter.convertListToDTO(orders);
        } else {
            orders = orderDAO.getAll();
            orderDTOs = OrderConverter.convertListToDTOAdmin(orders);
        }

        return ResponseEntity.ok().body(orderDTOs);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<?> createOrder(@AuthenticationPrincipal User user, @RequestBody @Valid OrderDTO orderDTO) {
        Order order = new Order();
        order.setUser(user);

        // Copy properties from OrderDTO to Order
        order.setId(orderDTO.getId());
        order.setOrderDate(orderDTO.getOrderDate());

        try {
            for (OrderProduct orderProduct : orderDTO.getOrderProducts()) {
                orderProduct.setOrder(order);

                Product product = entityManager.find(Product.class, orderProduct.getProduct().getId());
                orderProduct.setProduct(product);

                order.addOrderProduct(orderProduct);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(orderDAO.save(order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN') OR hasAuthority('USER')")
    public ResponseEntity<?> deleteOrder(@AuthenticationPrincipal User user, @PathVariable String id) {
        // Users can only return items within thirty days of placing order
        LocalDate today = LocalDate.now();
        LocalDate thirtyDaysAgo = today.minusDays(30);

        try {
            Date orderDate = orderDAO.getById(id).get().getOrderDate();
            boolean isOrderOver30DaysAgo = orderDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().isAfter(thirtyDaysAgo);

            if (user.getRole().getName().equals("USER") && isOrderOver30DaysAgo || user.getRole().getName().equals("ADMIN")) {
                this.orderDAO.deleteById(id);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
