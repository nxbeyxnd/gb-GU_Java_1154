package ru.gb.mall.inventory.repository;/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.repository 
*/

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.Warehouse;

import java.util.Optional;

public interface WarehouseRepository extends PagingAndSortingRepository<Warehouse, Long> {
    Optional<Warehouse> findByAddress(String address);
}
