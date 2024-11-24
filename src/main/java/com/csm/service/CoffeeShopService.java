package com.csm.service;

import com.csm.exception.BadRequestException;
import com.csm.mapper.CoffeeShopMapper;
import com.csm.model.ResponseStatusEnum;
import com.csm.model.request.CoffeeShopCreateRequest;
import com.csm.model.response.CoffeeShopCreateResponse;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.repository.CoffeeShopRepository;
import com.csm.repository.entity.CoffeeShop;
import com.csm.repository.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CoffeeShopService {

    @Autowired
    private CoffeeShopRepository coffeeShopRepository;

    @Autowired
    private CoffeeShopMapper coffeeShopMapper;

    @Autowired
    private UserDetailsService customUserDetailsService;

    public CoffeeShopCreateResponse createCoffeeShop(CoffeeShopCreateRequest coffeeShop) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        UserEntity userEntity = (UserEntity) customUserDetailsService.loadUserByUsername(username);
        CoffeeShop coffeeShopEntity = coffeeShopMapper.toEntity(coffeeShop);
        UserEntity user = new UserEntity();
        user.setId(userEntity.getId());
        coffeeShopEntity.setOwner(user);
        coffeeShopEntity = coffeeShopRepository.save(coffeeShopEntity);
        return coffeeShopMapper.toCoffeeShopCreateResponse(coffeeShopEntity);
    }

    public CoffeeShopGetResponse getCoffeeShop(Long id) {
        Optional<CoffeeShop> coffeeShopOptional = coffeeShopRepository.findById(id);
        if (coffeeShopOptional.isEmpty()) {
            throw new BadRequestException(ResponseStatusEnum.NOT_FOUND, "Coffee shop");
        }

        return coffeeShopMapper.toCoffeeShopGetResponse(coffeeShopOptional.get());
    }
}
