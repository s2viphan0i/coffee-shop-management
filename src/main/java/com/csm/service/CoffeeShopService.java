package com.csm.service;

import com.csm.exception.BadRequestException;
import com.csm.mapper.CoffeeShopMapper;
import com.csm.mapper.MenuMapper;
import com.csm.model.ResponseStatusEnum;
import com.csm.model.request.CoffeeShopCreateRequest;
import com.csm.model.request.MenuCreateRequest;
import com.csm.model.response.CoffeeShopCreateResponse;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.model.response.MenuGetResponse;
import com.csm.model.response.MenuUpdateResponse;
import com.csm.repository.CoffeeShopRepository;
import com.csm.repository.MenuRepository;
import com.csm.repository.entity.CoffeeShopEntity;
import com.csm.repository.entity.MenuEntity;
import com.csm.repository.entity.MenuItemEntity;
import com.csm.repository.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoffeeShopService {

    @Autowired
    private CoffeeShopRepository coffeeShopRepository;

    @Autowired
    private CoffeeShopMapper coffeeShopMapper;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public CoffeeShopCreateResponse createCoffeeShop(CoffeeShopCreateRequest coffeeShop) {
        UserEntity userEntity = customUserDetailsService.getCurrentUser();
        CoffeeShopEntity coffeeShopEntity = coffeeShopMapper.toEntity(coffeeShop);
        UserEntity user = new UserEntity();
        user.setId(userEntity.getId());
        coffeeShopEntity.setOwner(user);
        coffeeShopEntity = coffeeShopRepository.save(coffeeShopEntity);
        return coffeeShopMapper.toCoffeeShopCreateResponse(coffeeShopEntity);
    }

    public CoffeeShopGetResponse getCoffeeShop(Long id) {
        CoffeeShopEntity coffeeShopEntity = getCoffeeShopById(id);

        return coffeeShopMapper.toCoffeeShopGetResponse(coffeeShopEntity);
    }

    public MenuUpdateResponse updateShopMenu(Long shopId, MenuCreateRequest menuCreateRequest) {
        UserEntity userEntity = customUserDetailsService.getCurrentUser();
        CoffeeShopEntity coffeeShopEntity = getCoffeeShopById(shopId);

        if (userEntity == null || !userEntity.getId().equals(coffeeShopEntity.getOwner().getId())) {
            throw new BadRequestException(ResponseStatusEnum.INVALID_CREDENTIAL);
        }

        Optional<MenuEntity> menuEntityOptional = menuRepository.findByShopId(shopId);
        MenuEntity menuEntity = menuEntityOptional.orElseGet(MenuEntity::new);

        List<MenuItemEntity> menuItemEntities = menuCreateRequest.getItems().stream()
                .map(menuItemDTO -> menuMapper.toEntity(menuItemDTO))
                .toList();
        menuEntity.setItems(menuItemEntities);
        menuEntity.setShop(coffeeShopEntity);
        menuEntity = menuRepository.save(menuEntity);
        return menuMapper.toMenuUpdateResponse(menuEntity);
    }

    public CoffeeShopEntity getCoffeeShopById(Long id) {
        Optional<CoffeeShopEntity> coffeeShopOptional = coffeeShopRepository.findById(id);

        return coffeeShopOptional.orElseThrow(() -> new BadRequestException(ResponseStatusEnum.NOT_FOUND, "Coffee shop"));
    }

    public MenuGetResponse getShopMenu(Long shopId) {
        Optional<MenuEntity> menuEntityOptional = menuRepository.findByShopId(shopId);
        MenuEntity menuEntity = menuEntityOptional.orElseGet(MenuEntity::new);
        return menuMapper.toDTO(menuEntity);
    }

    public List<CoffeeShopGetResponse> getAllCoffeeShop() {
        List<CoffeeShopEntity> coffeeShops = coffeeShopRepository.findAll();

        return coffeeShops.stream().map(coffeeShopMapper::toCoffeeShopGetResponse).collect(Collectors.toList());
    }
}
