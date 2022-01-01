package ru.gb.mall.inventory.mail;

import ru.gb.mall.inventory.dto.WarehouseShipmentDto;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.mail.message.AttachmentMailMessage;
import ru.gb.mall.inventory.mail.message.EmailMessage;

public interface EmailService {
    void send(EmailMessage message);
    void send(AttachmentMailMessage message);

    void notifyAboutShipment(WarehouseKeeper keeper, WarehouseShipmentDto shipmentDto, String productName);
    void notifyAboutLowStock(WarehouseKeeper keeper, WarehouseItem item);
}
