package ru.gb.mall.inventory.service;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.service 
*/

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.gb.mall.inventory.dto.ProductRequestDto;
import ru.gb.mall.inventory.dto.WarehouseDto;
import ru.gb.mall.inventory.dto.WarehouseKeeperDto;
import ru.gb.mall.inventory.dto.WriteOffDto;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.exception.DuplicatedValueException;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.WarehouseItemRepository;
import ru.gb.mall.inventory.repository.WarehouseRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class WarehouseService {
    private final WarehouseRepository warehouseRepository;
    private final WarehouseItemRepository warehouseItemRepository;
    private final ProductService productService;

    public WarehouseService(WarehouseRepository warehouseRepository, WarehouseItemRepository warehouseItemRepository, ProductService productService) {
        this.warehouseRepository = warehouseRepository;
        this.warehouseItemRepository = warehouseItemRepository;
        this.productService = productService;
    }

    public Warehouse addNewWarehouse(WarehouseDto warehouseDto, WarehouseKeeperDto warehouseKeeperDto) {
        if (warehouseRepository.findByAddress(warehouseDto.getWarehouseAddress()).isPresent()) {
            throw new DuplicatedValueException(String.format("Warehouse with Address (%s) already exists", warehouseDto.getWarehouseAddress()));
        }
        Warehouse warehouse = new Warehouse();
        WarehouseKeeper warehouseKeeper = warehouseDto.getWarehouseKeeper();
        warehouse.setAddress(warehouseDto.getWarehouseAddress());
        warehouseKeeper.setEmail(warehouseKeeperDto.getEmail());
        warehouseKeeper.setName(warehouseKeeperDto.getEmail());
        warehouseKeeper.setWarehouse(warehouse);
        return warehouseRepository.save(warehouse);
    }

    public Warehouse findById(Long id) {
        try {
            return warehouseRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Warehouse entity no found by id: " + id, e);
        }
    }

    public List<Warehouse> findAll() {
        return StreamSupport.stream(warehouseRepository.findAll().spliterator(), true).toList();
    }

    public Warehouse updateWarehouse(Long id, WarehouseDto warehouseDto) {
        Warehouse warehouse = findById(id);
        warehouse.setAddress(warehouseDto.getWarehouseAddress());
        return warehouseRepository.save(warehouse);
    }

    public Long deleteWarehouseById(Long id) {
        try {
            warehouseRepository.findById(id).orElseThrow();
            warehouseRepository.deleteById(id);
            return id;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Warehouse entity not found by id: " + id, e);
        }
    }

    public WarehouseItem findByProductAndWarehouse(Long productid, Long warehouseid) {
        try {
            Product product = productService.findById(productid);
            Warehouse warehouse = findById(warehouseid);
            return warehouseItemRepository.findByProductAndWarehouse(product, warehouse);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("WarehouseItem entity not found by Product id" + productid + ") and Warehouse id (" + warehouseid + ")", e);
        }
    }

    public WarehouseItem writeOffWarehouseItemByProductAndWarehouse(WriteOffDto writeOffDto){
        WarehouseItem warehouseItem = findByProductAndWarehouse(writeOffDto.getProductId(), writeOffDto.getWarehouseId());
        warehouseItem.setAmount(warehouseItem.getAmount() - writeOffDto.getAmount());
        return warehouseItemRepository.save(warehouseItem);
    }

}
