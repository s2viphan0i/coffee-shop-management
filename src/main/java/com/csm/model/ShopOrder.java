package com.csm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShopOrder implements Serializable {
    private String username;
    private List<MenuItemDTO> order;
    private String note;
    private RegionEnum region;

    @Override
    public String toString() {
        return "ShopOrder{" +
                "username='" + username + '\'' +
                ", order=" + order +
                ", note='" + note + '\'' +
                ", region=" + region +
                '}';
    }
}