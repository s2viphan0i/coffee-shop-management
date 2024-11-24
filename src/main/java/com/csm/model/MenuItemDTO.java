package com.csm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuItemDTO {
    private String name;
    private String description;
    private double price;
}
