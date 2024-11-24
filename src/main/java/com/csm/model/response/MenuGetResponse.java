package com.csm.model.response;

import com.csm.model.MenuItemDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MenuGetResponse {
    private List<MenuItemDTO> items;
}
