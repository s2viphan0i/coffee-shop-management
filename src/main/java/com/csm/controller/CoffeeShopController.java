package com.csm.controller;

import com.csm.factory.ResponseFactory;
import com.csm.model.request.CoffeeShopCreateRequest;
import com.csm.model.response.CoffeeShopCreateResponse;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.service.CoffeeShopService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("")
public class CoffeeShopController {
    @Autowired
    private CoffeeShopService coffeeShopService;

    @PostMapping(value = "/admin/coffee-shops", produces = "application/json")
    public ResponseEntity<?> createCoffeeShop(@RequestBody CoffeeShopCreateRequest coffeeShop) {
        CoffeeShopCreateResponse response = coffeeShopService.createCoffeeShop(coffeeShop);
        return ResponseFactory.success(response, CoffeeShopCreateResponse.class);
    }

    @GetMapping(value = "/user/coffee-shops/{id}", produces = "application/json")
    public ResponseEntity<?> createCoffeeShop(@PathVariable @NotNull Long id) {
        CoffeeShopGetResponse response = coffeeShopService.getCoffeeShop(id);
        return ResponseFactory.success(response, CoffeeShopGetResponse.class);
    }
}
