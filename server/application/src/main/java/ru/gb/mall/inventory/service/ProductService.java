package ru.gb.mall.inventory.service;

import org.springframework.stereotype.Service;
import ru.gb.mall.inventory.dto.ProductRequestDto;
import ru.gb.mall.inventory.dto.ProductUpdateRequestDto;
import ru.gb.mall.inventory.entity.Product;
import ru.gb.mall.inventory.exception.DuplicatedValueException;
import ru.gb.mall.inventory.exception.EntityNotFoundException;
import ru.gb.mall.inventory.repository.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.StreamSupport;


@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final ProductDiscountService productDiscountService;
    private final ProductCategoryService productCategoryService;

    public ProductService(ProductRepository productRepository, ProductPriceService productPriceService, ProductDiscountService productDiscountService, ProductCategoryService productCategoryService) {
        this.productRepository = productRepository;
        this.productPriceService = productPriceService;
        this.productDiscountService = productDiscountService;
        this.productCategoryService = productCategoryService;
    }

    public List<Product> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), true).toList();
    }

    public Product findById(long id) {
        try {
            return productRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity no found by id: " + id, e);
        }
    }

    public Product findByName(ProductRequestDto productRequestDto){
        Optional<Product> productPresent = productRepository.findByName(productRequestDto.getName());
        if (productPresent.isPresent()) {
            return productPresent.get();
        }
        throw new DuplicatedValueException(String.format("Products with name (%s) already exists", productRequestDto.getName()));

    }

    @Transactional
    public Product addNewProduct(ProductRequestDto productRequestDto) {
        Optional<Product> productPresent = productRepository.findByName(productRequestDto.getName());
        if (productPresent.isPresent()) {
            throw new DuplicatedValueException(String.format("Products with name (%s) already exists", productRequestDto.getName()));
        }
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setCategory(productCategoryService.saveOrUpdate(productRequestDto.getCategory()));
        product.setPrice(productPriceService.saveOrUpdate(product.getId(), productRequestDto.getPrice()));
        product.setDiscount(productDiscountService.saveOrUpdate(product.getId(), productRequestDto.getDiscount()));
        return productRepository.save(product);
    }

    public Long deleteProductById(Long id) {
        try {
            productRepository.findById(id).orElseThrow();
            productRepository.deleteById(id);
            return id;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity not found by id: " + id, e);
        }
    }

    @Transactional
    public Product updateProductById(Long id, ProductUpdateRequestDto productUpdateRequestDto) {
        try {
            Product product = productRepository.findById(id).orElseThrow();
            product.setCategory(productCategoryService.saveOrUpdate(productUpdateRequestDto.getCategory()));
            product.setPrice(productPriceService.saveOrUpdate(product.getId(), productUpdateRequestDto.getPrice()));
            product.setDiscount(productDiscountService.saveOrUpdate(product.getId(), productUpdateRequestDto.getDiscount()));
            return productRepository.save(product);
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity not found by id: " + id, e);
        }
    }

    @Transactional
    public Long deleteCategoryById(Long id) {
        try {
            for (Product p : productRepository.findAllByCategory(productCategoryService.findById(id)).orElseThrow()){
                p.getCategory().remove(productCategoryService.findById(id));
                productRepository.save(p);
            }
            productCategoryService.deleteById(id);
            return id;
        } catch (NoSuchElementException e) {
            throw new EntityNotFoundException("Product entity not found by id: " + id, e);
        }
    }
}
