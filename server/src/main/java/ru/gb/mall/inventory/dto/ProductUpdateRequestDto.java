package ru.gb.mall.inventory.dto;

import lombok.Data;

import java.util.Set;

@Data
public class ProductUpdateRequestDto {
    private Set<String> category;
    private double price;
    private double discount;
}
