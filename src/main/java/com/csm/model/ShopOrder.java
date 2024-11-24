package com.csm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShopOrder {
    private UserDTO user;
    private List<MenuItemDTO> order;
    private String note;
}
