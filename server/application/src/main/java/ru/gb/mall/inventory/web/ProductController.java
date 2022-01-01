package ru.gb.mall.inventory.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mall.inventory.dto.ProductRequestDto;
import ru.gb.mall.inventory.dto.ProductUpdateRequestDto;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.service.ProductService;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(productService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<Product> addNewProduct(@RequestBody ProductRequestDto productRequestDto){
        return ResponseEntity.ok(productService.addNewProduct(productRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProductById(@PathVariable("id") long id, @RequestBody ProductUpdateRequestDto productUpdateRequestDto){
        return ResponseEntity.ok(productService.updateProductById(id, productUpdateRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> deleteProductById(@PathVariable("id") Long id){
        return new ResponseEntity<>(productService.deleteProductById(id), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Long> deleteCategoryById(@PathVariable ("id") Long id){
        return new ResponseEntity<>(productService.deleteCategoryById(id), HttpStatus.NO_CONTENT);
    }
}
