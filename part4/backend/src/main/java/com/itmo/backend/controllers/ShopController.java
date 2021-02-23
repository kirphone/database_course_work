package com.itmo.backend.controllers;

import com.itmo.backend.database.entity.ShopCompanyEntity;
import com.itmo.backend.database.repositories.ShopCompanyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api/shop")
public class ShopController {

    private final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private ShopCompanyRepository shopCompanyRepository;

    @GetMapping(value = "/getShopCompanies", produces = "application/json")
    public List<ShopCompanyEntity> getAllShopCompaniesNames() {
        List<ShopCompanyEntity> list = shopCompanyRepository.findAll();
        logger.info("возращает " + list.toString());

        return shopCompanyRepository.findAll();
    }
}
