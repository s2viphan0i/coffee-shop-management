package com.csm.repository.entity;

import com.csm.model.RegionEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "coffee_shop")
@Getter
@Setter
public class CoffeeShopEntity extends AuditEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column()
    private String address;

    @Column(name = "opening_hours")
    private String openingHours;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Integer queue;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RegionEnum region;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @OneToOne(mappedBy = "shop", cascade = CascadeType.ALL)
    private MenuEntity menu;
}
