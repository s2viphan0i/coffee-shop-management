package com.csm.job;

import com.csm.model.QuadTree;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.service.CoffeeShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ScheduledJobs {

    @Autowired
    private CoffeeShopService coffeeShopService;

    public static QuadTree world = new QuadTree(new QuadTree.BoundingBox(-90, -180, 90, 180));

    // Runs daily at 0 AM
    @Scheduled(cron = "0 0 0 * * ?")
    public void runDailyTask() {
        log.info("Generate quadtree for recommendation");
        generateQuadTree();
    }

    // Runs once when the application starts
    @EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void runAtStartup() {
        log.info("Generate quadtree for recommendation");
        generateQuadTree();
    }

    private void generateQuadTree() {
        world = new QuadTree(new QuadTree.BoundingBox(-90, -180, 90, 180));
        List<CoffeeShopGetResponse> coffeeShops = coffeeShopService.getAllCoffeeShop();
        for (CoffeeShopGetResponse coffeeShop : coffeeShops) {
            world.insert(new QuadTree.Point(coffeeShop.getLatitude(), coffeeShop.getLongitude(), coffeeShop.getId()));
        }
    }
}