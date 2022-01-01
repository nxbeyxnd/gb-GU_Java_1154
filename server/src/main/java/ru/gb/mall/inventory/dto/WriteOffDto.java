package ru.gb.mall.inventory.dto;
/* 
01.01.2022: Alexey created this file inside the package: ru.gb.mall.inventory.dto 
*/

import lombok.Data;

@Data
public class WriteOffDto {
    private Long productId;
    private Long warehouseId;
    private int amount;
}
