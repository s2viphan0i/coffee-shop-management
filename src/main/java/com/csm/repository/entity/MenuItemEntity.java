package com.csm.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MenuItemEntity {
    private String name;
    private String description;
    private double price;
}
