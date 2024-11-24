package com.csm.mapper;

import com.csm.model.request.CoffeeShopCreateRequest;
import com.csm.model.response.CoffeeShopCreateResponse;
import com.csm.model.response.CoffeeShopGetResponse;
import com.csm.model.response.OwnerGetResponse;
import com.csm.repository.entity.CoffeeShopEntity;
import org.springframework.stereotype.Component;

@Component
public class CoffeeShopMapper {

    public CoffeeShopEntity toEntity(CoffeeShopCreateRequest coffeeShopDTO) {
        if (coffeeShopDTO == null) {
            return null;
        }

        CoffeeShopEntity coffeeShopEntity = new CoffeeShopEntity();
        coffeeShopEntity.setName(coffeeShopDTO.getName());
        coffeeShopEntity.setAddress(coffeeShopDTO.getAddress());
        coffeeShopEntity.setOpeningHours(coffeeShopDTO.getOpeningHours());
        coffeeShopEntity.setLatitude(coffeeShopDTO.getLatitude());
        coffeeShopEntity.setLongitude(coffeeShopDTO.getLongitude());

        return coffeeShopEntity;
    }

    public CoffeeShopGetResponse toCoffeeShopGetResponse(CoffeeShopEntity coffeeShopEntity) {
        if (coffeeShopEntity == null) {
            return null;
        }

        CoffeeShopGetResponse coffeeShopDTO = new CoffeeShopGetResponse();
        coffeeShopDTO.setId(coffeeShopEntity.getId());
        coffeeShopDTO.setName(coffeeShopEntity.getName());
        coffeeShopDTO.setAddress(coffeeShopEntity.getAddress());
        coffeeShopDTO.setOpeningHours(coffeeShopEntity.getOpeningHours());
        coffeeShopDTO.setLatitude(coffeeShopEntity.getLatitude());
        coffeeShopDTO.setLongitude(coffeeShopEntity.getLongitude());
        coffeeShopDTO.setQueues(coffeeShopEntity.getQueues());
        OwnerGetResponse ownerDTO = new OwnerGetResponse();
        ownerDTO.setName(coffeeShopEntity.getOwner().getFullname());
        ownerDTO.setPhone(coffeeShopEntity.getOwner().getPhone());
        coffeeShopDTO.setOwner(ownerDTO);
        return coffeeShopDTO;
    }

    public CoffeeShopCreateResponse toCoffeeShopCreateResponse(CoffeeShopEntity coffeeShopEntity) {
        if (coffeeShopEntity == null) {
            return null;
        }

        CoffeeShopCreateResponse response = new CoffeeShopCreateResponse();
        response.setId(coffeeShopEntity.getId());
        return response;
    }
}
