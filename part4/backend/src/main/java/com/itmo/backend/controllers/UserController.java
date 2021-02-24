package com.itmo.backend.controllers;

import com.itmo.backend.database.entity.*;
import com.itmo.backend.database.repositories.*;
import com.itmo.backend.security.AccountPrincipal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private OrderProductRepository orderProductRepository;

    @Autowired
    private ProductShopRepository productShopRepository;

    @Autowired
    private StatusOrderRepository statusOrderRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> getOrdersAsCustomer() {
        Integer userId = ((AccountPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        logger.info("Get orders for id=" + userId);
        List<OrderEntity> orders = orderRepository.findOrderByUserId(userId);
        return ok(orders);
    }

    @GetMapping("/orders/all")
    public ResponseEntity<List<AddressEmbeddedEntity>> getOrdersAsCourier(){
        return ok(orderRepository.findAllWithoutCourier());
    }

    @GetMapping("/order/{id}/messages")
    public ResponseEntity<List<MessageEntity>> getMessagesByOrder(@PathVariable("id") Integer orderId){
        return ok(messageRepository.findByOrderId(orderId));
    }

    @PostMapping("/orders")
    public ResponseEntity<HashMap<String, Integer>> addOrder(@RequestBody OrderRequest req){
        Integer userId = ((AccountPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        OrderEntity order = OrderEntity.builder().customer(accountRepository.findById(userId).get())
                .address(new AddressEmbeddedEntity(req.getLat(), req.getLng()))
                .shop(shopRepository.findById(req.getShopId()).get())
                .status(statusOrderRepository.findByName("Поиск курьера").get()).build();

        Integer orderId = orderRepository.save(order).getId();

        req.getProducts().forEach((product) -> {
            OrderProductEntity note = OrderProductEntity.builder().id(new OrderProductKey(orderId, product.getProductId()))
                    .productCount(product.getProductCount()).needConfirm(product.getNeedConfirm())
                    .price(productShopRepository.selectProductPriceByShopIdAndProductId(req.getShopId(), product.getProductId()))
                    .order(orderRepository.findById(orderId).get())
                    .product(productRepository.findById(product.getProductId()).get()).build();
            orderProductRepository.save(note);
        });
        return ok(new HashMap<String, Integer>(){{
            put("orderId", orderId);
        }});
    }

    @PostMapping("/order/{id}/messages")
    public ResponseEntity<HashMap<String, Integer>> addMessage(@PathVariable("id") Integer orderId, @RequestBody String messageText){
        Integer userId = ((AccountPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        MessageEntity message = MessageEntity.builder().text(messageText).sendTime(OffsetDateTime.now())
                .sender(accountRepository.findById(userId).get())
                .order(orderRepository.findById(orderId).get()).build();
        Integer messageId = messageRepository.save(message).getId();
        return ok(new HashMap<String, Integer>(){{
            put("messageId", messageId);
        }});
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class OrderRequest {

    private Double lat;
    private Double lng;
    private Integer shopId;
    private List<ProductToAdd> products;
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class ProductToAdd{

    private Integer productId;

    private Integer productCount;

    private Boolean needConfirm;
}


