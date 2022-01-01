package ru.gb.mall.inventory.entity;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.entity 
*/

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSES")
@Data
public class Warehouse {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_warehouseId", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ADDRESS")
    private String address;
}
