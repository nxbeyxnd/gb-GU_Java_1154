package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.ProductCategory;

import java.util.List;
import java.util.Set;

public interface ProductCategoryRepository extends PagingAndSortingRepository<ProductCategory, Long> {
    List<ProductCategory> findAllByNameIn(Set<String> productCategorySet);
    ProductCategory findByName(String name);
}
