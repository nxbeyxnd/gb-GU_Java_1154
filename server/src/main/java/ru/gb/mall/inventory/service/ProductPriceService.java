package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.ProductPrice;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductPriceRepository;

import java.util.NoSuchElementException;

@Service
public class ProductPriceService {
    private final ProductPriceRepository productPriceRepository;

    public ProductPriceService(ProductPriceRepository productPriceRepository) {
        this.productPriceRepository = productPriceRepository;
    }

    public ProductPrice findById(Long productId){
        try{
            return productPriceRepository.findById(productId).orElseThrow();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("Product with id (" + productId + ") is not exists", e);
        }
    }

    public ProductPrice saveOrUpdate(Long id, double price){
        ProductPrice productPrice = new ProductPrice();
        productPrice.setId(id);
        productPrice.setOriginalValue(price);
        return productPriceRepository.save(productPrice);
    }
}
