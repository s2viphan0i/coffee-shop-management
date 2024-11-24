package com.csm.model.request;

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
public class CoffeeShopCreateRequest {
    @NotBlank
    private String name;
    private String address;
    private String openingHours;
    @NotNull
    private Double latitude;
    @NotNull
    private Double longitude;
    @JsonIgnore
    private Long ownerId;
    private Integer queue;
    @NotNull
    private RegionEnum region;
}
