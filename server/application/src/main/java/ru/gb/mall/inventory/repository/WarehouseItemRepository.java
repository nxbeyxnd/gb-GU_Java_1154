package ru.gb.mall.inventory.repository;/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.repository 
*/

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;

public interface WarehouseItemRepository extends PagingAndSortingRepository<WarehouseItem, Long> {
    WarehouseItem findByProductAndWarehouse(Product product, Warehouse warehouse);
}
