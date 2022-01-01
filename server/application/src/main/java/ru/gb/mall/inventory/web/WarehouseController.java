package ru.gb.mall.inventory.web;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.web 
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.dto.WarehouseDto;
import ru.gb.mall.inventory.dto.WarehouseKeeperDto;
import ru.gb.mall.inventory.dto.WriteOffDto;
import ru.gb.mall.inventory.entity.Warehouse;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.service.WarehouseItemService;
import ru.gb.mall.inventory.service.WarehouseKeeperService;
import ru.gb.mall.inventory.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final WarehouseItemService warehouseItemService;
    private final WarehouseKeeperService warehouseKeeperService;

    public WarehouseController(WarehouseService warehouseService, WarehouseItemService warehouseItemService, WarehouseKeeperService warehouseKeeperService) {
        this.warehouseService = warehouseService;
        this.warehouseItemService = warehouseItemService;
        this.warehouseKeeperService = warehouseKeeperService;
    }

    @PostMapping
    public ResponseEntity<Warehouse> addNewWarehouse(@RequestBody WarehouseDto warehouseDto, @RequestBody WarehouseKeeperDto warehouseKeeperDto) {
        return ResponseEntity.ok(warehouseService.addNewWarehouse(warehouseDto, warehouseKeeperDto));
    }

    @PostMapping("/writeoff")
    public ResponseEntity<WarehouseItem> writeOffWarehouseItemByProductAndWarehouse(@RequestBody WriteOffDto writeOffDto){
        return  ResponseEntity.ok(warehouseService.writeOffWarehouseItemByProductAndWarehouse(writeOffDto));
    }

    @GetMapping("/{productId}{warehouseId}")
    public ResponseEntity<WarehouseItem> findByProductIdAndWarehouseID(@PathVariable("productId") Long productid, @PathVariable("warehouseId") Long warehouseid){
        return ResponseEntity.ok(warehouseService.findByProductAndWarehouse(productid, warehouseid));
    }

    @GetMapping
    public ResponseEntity<List<Warehouse>> findAll(){
        return ResponseEntity.ok(warehouseService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findWarehouseById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(warehouseService.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> updateWarehouse(@PathVariable("id") Long id, @RequestBody WarehouseDto warehouseDto){
        return ResponseEntity.ok(warehouseService.updateWarehouse(id, warehouseDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable("id") Long id){
        return new ResponseEntity<>(warehouseService.deleteWarehouseById(id), HttpStatus.NO_CONTENT);
    }
}
