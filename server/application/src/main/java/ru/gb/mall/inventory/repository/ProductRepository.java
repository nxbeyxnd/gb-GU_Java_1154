package ru.gb.mall.inventory.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.entity.ProductCategory;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Optional<Product> findByName(String name);
    Optional<List<Product>> findAllByCategory(ProductCategory productCategory);
}
