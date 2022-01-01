package ru.gb.mall.inventory.web;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.web 
*/

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.mall.inventory.service.WarehouseItemService;

@RestController
@RequestMapping("/item")
public class WarehouseItemController {
    private final WarehouseItemService warehouseItemService;

    public WarehouseItemController(WarehouseItemService warehouseItemService) {
        this.warehouseItemService = warehouseItemService;
    }




}
