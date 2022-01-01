package ru.gb.mall.inventory.web;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.web 
*/

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.dto.WarehouseKeeperDto;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.service.WarehouseKeeperService;

import java.util.List;

@RestController
@RequestMapping("/keeper")
public class WarehouseKeeperController {
    private final WarehouseKeeperService warehouseKeeperService;

    public WarehouseKeeperController(WarehouseKeeperService warehouseKeeperService) {
        this.warehouseKeeperService = warehouseKeeperService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseKeeper> findByID(@PathVariable("id") Long id){
        return ResponseEntity.ok(warehouseKeeperService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<WarehouseKeeper>> findAll(){
        return ResponseEntity.ok(warehouseKeeperService.findAll());
    }

    @PostMapping
    public ResponseEntity<WarehouseKeeper> createNewWarehouseKeeper(@RequestBody WarehouseKeeperDto warehouseKeeperDto){
        return ResponseEntity.ok(warehouseKeeperService.createNewWarehouseKeeper(warehouseKeeperDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseKeeper> updateWarehouseKeeperById(@PathVariable("id") Long id, @RequestBody WarehouseKeeperDto warehouseKeeperDto){
        return ResponseEntity.ok(warehouseKeeperService.updateWarehouseKeeper(id, warehouseKeeperDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteWarehouseKeeper(@PathVariable("id") Long id){
        return new ResponseEntity<>(warehouseKeeperService.deleteWarehouseById(id), HttpStatus.BAD_REQUEST);
    }
}
