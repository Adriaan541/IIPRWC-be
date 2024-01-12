package com.example.iprwcbe.DTOs;

import com.example.iprwcbe.model.database.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderConverter {
    public static OrderDTO convertToDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderProducts(order.getOrderProducts());
        return dto;
    }

    public static List<OrderDTO> convertListToDTO(List<Order> orders) {
        List<OrderDTO> dtos = new ArrayList<>();
        for (Order order : orders) {
            dtos.add(convertToDTO(order));
        }
        return dtos;
    }

    public static OrderDTO convertToDTOAdmin(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setUser(order.getUser());
        dto.setOrderDate(order.getOrderDate());
        dto.setOrderProducts(order.getOrderProducts());
        return dto;
    }

    public static List<OrderDTO> convertListToDTOAdmin(List<Order> orders) {
        List<OrderDTO> dtos = new ArrayList<>();
        for (Order order : orders) {
            dtos.add(convertToDTOAdmin(order));
        }
        return dtos;
    }


}
