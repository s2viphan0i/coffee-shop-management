package com.csm.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class MenuItemDTO implements Serializable {
    private String name;
    private String description;
    private double price;
}
