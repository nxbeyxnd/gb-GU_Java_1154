package ru.gb.mall.inventory.entity;
/* 
26.12.2021: Alexey created this file inside the package: ru.gb.mall.inventory.entity 
*/

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSEKEEPER")
@Data
public class WarehouseKeeper {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_WarehouseKeeperId", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne
    @JoinColumn(
            name = "warehouse_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "FK_WAREHOUSEKEEPER_WAREHOUSE_ID_RELATION")
    )
    private Warehouse warehouse;
}
