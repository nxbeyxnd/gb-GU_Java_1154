package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "PRODUCTS")
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_productId", allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true, columnDefinition = "VARCHAR", length = 50)
    private String name;

    @ManyToMany
    @JoinTable(
            name = "PRODUCT_CATEGORY_RELATION",
            joinColumns = @JoinColumn(
                    name = "PRODUCT_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_PRODUCT_CATEGORY_PRODUCT_ID_RELATION",
                            foreignKeyDefinition = "FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE NO ACTION ON UPDATE NO ACTION"
                    )

            ),
            inverseJoinColumns = @JoinColumn(
                    name = "CATEGORY_ID",
                    nullable = false,
                    foreignKey = @ForeignKey(
                            name = "FK_PRODUCT_CATEGORY_CATEGORY_ID_RELATION",
                            foreignKeyDefinition = "FOREIGN KEY (category_id) REFERENCES product_categories(id) ON DELETE CASCADE ON UPDATE NO ACTION"
                    )

            )
    )
    private List<ProductCategory> category;

    @ManyToOne
    @JoinColumn(
            name = "price_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCT_PRICE_PRODUCT_ID_RELATION")
    )
    private ProductPrice price;

    @ManyToOne
    @JoinColumn(
            name = "discount_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCT_DISCOUNT_PRODUCT_ID_RELATION")
    )
    private ProductDiscount discount;
}
