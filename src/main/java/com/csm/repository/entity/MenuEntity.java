package com.csm.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import java.util.List;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "menu")
public class MenuEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shop_id", nullable = false)
    private CoffeeShopEntity shop;

    @Type(JsonBinaryType.class)
    @Column(columnDefinition = "jsonb")
    private List<MenuItemEntity> items;

}
