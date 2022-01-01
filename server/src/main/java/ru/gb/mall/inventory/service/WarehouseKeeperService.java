package ru.gb.mall.inventory.service;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.service 
*/

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.dto.WarehouseKeeperDto;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.exception.DuplicatedValueException;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.WarehouseKeeperRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.StreamSupport;

@Service
public class WarehouseKeeperService {
    private final WarehouseKeeperRepository warehouseKeeperRepository;

    public WarehouseKeeperService(WarehouseKeeperRepository warehouseKeeperRepository) {
        this.warehouseKeeperRepository = warehouseKeeperRepository;
    }

    public WarehouseKeeper findById(Long id){
        try {
            return warehouseKeeperRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("WarehouseKeeper entity no found by id: " + id, e);
        }
    }

    public List<WarehouseKeeper> findAll(){
        return StreamSupport.stream(warehouseKeeperRepository.findAll().spliterator(), true).toList();
    }

    public WarehouseKeeper createNewWarehouseKeeper(WarehouseKeeperDto warehouseKeeperDto){
        if (warehouseKeeperRepository.findByName(warehouseKeeperDto.getName()).isPresent()){
            throw new DuplicatedValueException(String.format("Warehouse Keeper with name (%s) already exists", warehouseKeeperDto.getName()));
        }
        WarehouseKeeper warehouseKeeper = new WarehouseKeeper();
        warehouseKeeper.setName(warehouseKeeper.getName());
        warehouseKeeper.setEmail(warehouseKeeper.getEmail());
        warehouseKeeper.setWarehouse(warehouseKeeper.getWarehouse());
        return warehouseKeeperRepository.save(warehouseKeeper);
    }

    public WarehouseKeeper updateWarehouseKeeper(Long id, WarehouseKeeperDto warehouseKeeperDto){
        WarehouseKeeper warehouseKeeper = findById(id);
        warehouseKeeper.setName(warehouseKeeperDto.getName());
        warehouseKeeper.setEmail(warehouseKeeperDto.getEmail());
        warehouseKeeper.setWarehouse(warehouseKeeperDto.getWarehouse());
        return warehouseKeeperRepository.save(warehouseKeeper);
    }

    public Long deleteWarehouseById(Long id){
        try {
            warehouseKeeperRepository.findById(id).orElseThrow();
            warehouseKeeperRepository.deleteById(id);
            return id;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("WarehouseKeeper entity not found by id: " + id, e);
        }
    }
}
