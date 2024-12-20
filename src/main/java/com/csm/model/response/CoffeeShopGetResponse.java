package com.csm.model.response;

import com.csm.model.RegionEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CoffeeShopGetResponse {
    private Long id;
    private String name;
    private String address;
    private String openingHours;
    private Double latitude;
    private Double longitude;
    private Integer queues;
    private OwnerGetResponse owner;
    private RegionEnum region;
}
