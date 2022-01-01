package ru.gb.mall.inventory.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductRequestDto {
    private String name;
    private Set<String> category;
    private double price;
    private double discount;
}
