package com.csm.mapper;

import com.csm.model.request.CoffeeShopCreateRequest;
import com.csm.model.response.CoffeeShopCreateResponse;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.model.response.OwnerGetResponse;
import com.csm.repository.entity.CoffeeShop;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShopMapper {

    public CoffeeShop toEntity(CoffeeShopCreateRequest coffeeShopDTO) {
        if (coffeeShopDTO == null) {
            return null;
        }

        CoffeeShop coffeeShop = new CoffeeShop();
        coffeeShop.setName(coffeeShopDTO.getName());
        coffeeShop.setAddress(coffeeShopDTO.getAddress());
        coffeeShop.setOpeningHours(coffeeShopDTO.getOpeningHours());
        coffeeShop.setLatitude(coffeeShopDTO.getLatitude());
        coffeeShop.setLongitude(coffeeShopDTO.getLongitude());

        return coffeeShop;
    }

    public CoffeeShopGetResponse toCoffeeShopGetResponse(CoffeeShop coffeeShop) {
        if (coffeeShop == null) {
            return null;
        }

        CoffeeShopGetResponse coffeeShopDTO = new CoffeeShopGetResponse();
        coffeeShopDTO.setName(coffeeShop.getName());
        coffeeShopDTO.setAddress(coffeeShop.getAddress());
        coffeeShopDTO.setOpeningHours(coffeeShop.getOpeningHours());
        coffeeShopDTO.setLatitude(coffeeShop.getLatitude());
        coffeeShopDTO.setLongitude(coffeeShop.getLongitude());
        coffeeShopDTO.setLongitude(coffeeShop.getLongitude());
        OwnerGetResponse ownerDTO = new OwnerGetResponse();
        ownerDTO.setName(coffeeShop.getOwner().getFullname());
        ownerDTO.setPhone(coffeeShop.getOwner().getPhone());
        coffeeShopDTO.setOwner(ownerDTO);
        return coffeeShopDTO;
    }

    public CoffeeShopCreateResponse toCoffeeShopCreateResponse(CoffeeShop coffeeShop) {
        if (coffeeShop == null) {
            return null;
        }

        CoffeeShopCreateResponse response = new CoffeeShopCreateResponse();
        response.setId(coffeeShop.getId());
        return response;
    }
}
