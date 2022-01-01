package ru.gb.mall.inventory.web;


import java.util.Date;

public record ApiError(int code, String message, Date time) {
}
