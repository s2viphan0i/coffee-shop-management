package com.csm.model.request;

import com.csm.model.MenuItemDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuCreateRequest {
    @NotEmpty
    private List<MenuItemDTO> items;
}
