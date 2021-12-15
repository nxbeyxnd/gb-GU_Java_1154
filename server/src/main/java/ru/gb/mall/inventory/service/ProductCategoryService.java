package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.entity.ProductCategory;
import ru.gb.mall.inventory.repository.ProductCategoryRepository;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service
public class ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    public ProductCategoryService(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Transactional
    public List<ProductCategory> saveOrUpdate(Set<String> names){
        for (String s: names){
            if (productCategoryRepository.findByName(s) == null) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setName(s);
                productCategoryRepository.save(productCategory);
            }
        }
        return productCategoryRepository.findAllByNameIn(names);
    }
}
