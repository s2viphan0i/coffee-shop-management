package com.csm.repository;

import com.csm.repository.entity.CoffeeShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRepository extends JpaRepository<CoffeeShopEntity, Long> {
}
