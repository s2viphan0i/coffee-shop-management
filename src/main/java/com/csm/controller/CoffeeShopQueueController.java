package com.csm.controller;

import com.csm.factory.ResponseFactory;
import com.csm.model.ShopOrder;
import com.csm.model.ShopQueue;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.service.CoffeeShopQueueService;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("")
public class CoffeeShopQueueController {

  @Autowired
  private CoffeeShopQueueService coffeeShopQueueService;

  @PostMapping(value = "/user/coffee-shops/{shopId}/queue/{queueId}", produces = "application/json")
  public ResponseEntity<?> queue(@PathVariable @NotNull Long shopId, @RequestBody ShopOrder shopOrder, @PathVariable @NotNull String queueId) {
    coffeeShopQueueService.requestQueue(queueId, shopOrder);
    return ResponseFactory.success();
  }

  @GetMapping(value = "/user/coffee-shops/{shopId}/queue", produces = "application/json")
  public ResponseEntity<?> queue(@PathVariable @NotNull Long shopId) {
    List<ShopQueue> response =  coffeeShopQueueService.getShopQueue(shopId);
    return ResponseFactory.success(response, List.class);
  }
}