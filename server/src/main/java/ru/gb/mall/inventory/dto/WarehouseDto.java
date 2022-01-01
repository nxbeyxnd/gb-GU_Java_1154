package ru.gb.mall.inventory.dto;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.dto 
*/

import lombok.Data;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseKeeper;

@Data
public class WarehouseDto {
    private String WarehouseAddress;
    private WarehouseKeeper warehouseKeeper;
}
