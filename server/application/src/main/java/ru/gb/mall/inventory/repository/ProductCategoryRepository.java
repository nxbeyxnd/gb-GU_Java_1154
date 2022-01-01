package ru.gb.mall.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.mall.inventory.entity.ProductCategory;

import java.util.List;
import java.util.Set;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> findAllByNameIn(Set<String> productCategorySet);
    ProductCategory findByName(String name);
}
