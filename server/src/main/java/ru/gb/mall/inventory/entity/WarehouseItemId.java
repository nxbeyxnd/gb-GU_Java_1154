package ru.gb.mall.inventory.entity;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.entity 
*/

import java.io.Serializable;

public class WarehouseItemId implements Serializable {
    private Long warehouse;
    private Long product;

    public WarehouseItemId(Long warehouse, Long product) {
        this.warehouse = warehouse;
        this.product = product;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
