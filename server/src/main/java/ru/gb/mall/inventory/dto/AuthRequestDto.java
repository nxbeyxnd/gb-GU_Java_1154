package ru.gb.mall.inventory.dto;

import lombok.Data;

@Data
public class AuthRequestDto {
    private String login;
    private String password;

}
