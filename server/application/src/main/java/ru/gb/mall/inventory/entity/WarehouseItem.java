package ru.gb.mall.inventory.entity;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.entity 
*/

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSEITEM")
@Data
@IdClass(WarehouseItemId.class)
public class WarehouseItem {
    @Id
    @ManyToOne
    @JoinColumn(
            name = "warehouse_id",
            nullable = false,
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_WAREHOUSE_ITEM_WAREHOUSE_ID_RELATION")
    )
    private Warehouse warehouse;

    @Id
    @ManyToOne
    @JoinColumn(
            name = "product_id",
            nullable = false,
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_WAREHOUSE_ITEM_PRODUCT_ID_RELATION")
    )
    private Product product;

    @Column(name = "AMOUNT")
    private Long amount;
}
