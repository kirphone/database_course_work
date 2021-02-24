package com.itmo.backend.controllers;

import com.itmo.backend.database.entity.AccountEntity;
import com.itmo.backend.database.entity.OrderEntity;
import com.itmo.backend.database.entity.ProductShopEntity;
import com.itmo.backend.database.repositories.AccountRepository;
import com.itmo.backend.database.repositories.OrderRepository;
import com.itmo.backend.database.repositories.ShopCompanyRepository;
import com.itmo.backend.security.AccountPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getOrdersAsCustomer() {
        Integer id = ((AccountPrincipal)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        List<OrderEntity> orders = orderRepository.findOrderByUserId(id);
        return ok(orders);
    }
}
