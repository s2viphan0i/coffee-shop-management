package com.csm.mapper;

import com.csm.model.MenuItemDTO;
import com.csm.model.request.MenuCreateRequest;
import com.csm.model.response.MenuGetResponse;
import com.csm.model.response.MenuUpdateResponse;
import com.csm.repository.entity.MenuEntity;
import com.csm.repository.entity.MenuItemEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MenuMapper {

    public MenuEntity toEntity(MenuCreateRequest menuDTO) {
        MenuEntity menu = new MenuEntity();
        menu.setItems(
            menuDTO.getItems().stream().map(this::toEntity).collect(Collectors.toList())
        );
        return menu;
    }

    public MenuItemEntity toEntity(MenuItemDTO menuItemDTO) {
        MenuItemEntity menuItem = new MenuItemEntity();
        menuItem.setName(menuItemDTO.getName());
        menuItem.setDescription(menuItemDTO.getDescription());
        menuItem.setPrice(menuItemDTO.getPrice());
        return menuItem;
    }

    public MenuGetResponse toDTO(MenuEntity menu) {
        MenuGetResponse menuDTO = new MenuGetResponse();
        menuDTO.setItems(
            menu.getItems().stream().map(this::toDTO).collect(Collectors.toList())
        );
        return menuDTO;
    }

    public MenuUpdateResponse toMenuUpdateResponse(MenuEntity menu) {
        MenuUpdateResponse menuUpdateResponse = new MenuUpdateResponse();
        menuUpdateResponse.setId(menu.getId());
        return menuUpdateResponse;
    }

    public MenuItemDTO toDTO(MenuItemEntity menuItem) {
        MenuItemDTO menuItemDTO = new MenuItemDTO();
        menuItemDTO.setName(menuItem.getName());
        menuItemDTO.setDescription(menuItem.getDescription());
        menuItemDTO.setPrice(menuItem.getPrice());
        return menuItemDTO;
    }
}
