package com.csm.controller;

import com.csm.factory.ResponseFactory;
import com.csm.model.request.CoffeeShopCreateRequest;
import com.csm.model.request.MenuCreateRequest;
import com.csm.model.response.CoffeeShopCreateResponse;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.model.response.MenuGetResponse;
import com.csm.model.response.MenuUpdateResponse;
import com.csm.service.CoffeeShopService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping(value = "/admin/coffee-shops/{shopId}/menu", produces = "application/json")
    public ResponseEntity<?> updateShopMenu(@PathVariable Long shopId, @RequestBody MenuCreateRequest menuCreateRequest) {
        MenuUpdateResponse response = coffeeShopService.updateShopMenu(shopId, menuCreateRequest);
        return ResponseFactory.success(response, MenuUpdateResponse.class);
    }

    @GetMapping(value = "/user/coffee-shops/{id}", produces = "application/json")
    public ResponseEntity<?> getCoffeeShop(@PathVariable @NotNull Long id) {
        CoffeeShopGetResponse response = coffeeShopService.getCoffeeShop(id);
        return ResponseFactory.success(response, CoffeeShopGetResponse.class);
    }

    @GetMapping(value = "/user/coffee-shops/{shopId}/menu", produces = "application/json")
    public ResponseEntity<?> getShopMenu(@PathVariable Long shopId) {
        MenuGetResponse response = coffeeShopService.getShopMenu(shopId);
        return ResponseFactory.success(response, MenuGetResponse.class);
    }
}
