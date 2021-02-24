package com.itmo.backend.controllers;

import com.itmo.backend.database.entity.ProductEntity;
import com.itmo.backend.database.entity.ProductShopEntity;
import com.itmo.backend.database.entity.ShopCompanyEntity;
import com.itmo.backend.database.entity.ShopEntity;
import com.itmo.backend.database.repositories.ProductShopRepository;
import com.itmo.backend.database.repositories.ShopCompanyRepository;
import com.itmo.backend.database.repositories.ShopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.TypedQuery;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(path = "/api/shop")
public class ShopController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ShopCompanyRepository shopCompanyRepository;

    @Autowired
    private ProductShopRepository productShopRepository;

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping(value = "/getShopCompanies", produces = "application/json")
    public List<ShopCompanyEntity> getAllShopCompaniesNames() {
        List<ShopCompanyEntity> list = shopCompanyRepository.findAll();
        logger.info("возращает " + list.toString());

        return shopCompanyRepository.findAll();
    }

    @GetMapping("/{id}/products")
    public ResponseEntity<List<ProductShopEntity>> productsWithPriceByShopId(@PathVariable("id") Integer shopId) {
        return ok(productShopRepository.selectProductsByShopId(shopId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ShopEntity>> getAllShops(){
        return ok(shopRepository.findAll());
    }
}
