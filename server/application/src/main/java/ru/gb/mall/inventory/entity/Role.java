package ru.gb.mall.inventory.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "ROLES")
@Entity
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_idGenerator")
    @SequenceGenerator(name = "seq_idGenerator", sequenceName = "seq_roleId", allocationSize = 1)
    @Column(name = "ID")
    private long id;

    @Column(name = "NAME", nullable = false, unique = true, columnDefinition = "VARCHAR", length = 10)
    private String name;

}
