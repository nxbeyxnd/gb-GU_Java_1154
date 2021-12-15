package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.ProductDiscount;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductDiscountRepository;

import java.util.NoSuchElementException;

@Service
public class ProductDiscountService {
    private final ProductDiscountRepository productDiscountRepository;

    public ProductDiscountService(ProductDiscountRepository productDiscountRepository) {
        this.productDiscountRepository = productDiscountRepository;
    }

    public ProductDiscount findById(Long productId) {
        try {
            return productDiscountRepository.findById(productId).orElseThrow();
        } catch (NoSuchElementException e){
            throw new EntityNotFoundException("Product with id (" + productId + ") is not exists", e);
        }
    }

    public ProductDiscount saveOrUpdate(Long id, double discount){
        ProductDiscount productDiscount = new ProductDiscount();
        productDiscount.setId(id);
        productDiscount.setOriginalValue(discount);
        return productDiscountRepository.save(productDiscount);
    }
}
