package ru.gb.mall.inventory.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import ru.gb.mall.inventory.dto.WarehouseShipmentDto;
import ru.gb.mall.inventory.entity.WarehouseItem;
import ru.gb.mall.inventory.entity.WarehouseKeeper;
import ru.gb.mall.inventory.mail.message.AttachmentMailMessage;
import ru.gb.mall.inventory.mail.message.EmailMessage;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
@Slf4j
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender sender;
    private final String SENDER = "Transport Company. noReply";

    public EmailServiceImpl(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void send(EmailMessage message) {
        log.info("Preparing mail message to be sent.");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(message.from());
        simpleMailMessage.setTo(message.to());
        simpleMailMessage.setSubject(message.subject());
        simpleMailMessage.setText(message.text());

        try {
            sender.send(simpleMailMessage);
        } catch (MailException e) {
            log.debug("While sending the mail the error occurred.");
            log.debug("Mail message: {}", message);
            throw new MailSenderException("Something when wrong while sending the mail.", e);
        }
    }

    @Override
    public void send(AttachmentMailMessage message) {
        log.info("Preparing mail mime-message to be sent.");

        MimeMessage mimeMessage = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

        try {
            helper.setFrom(message.from());
            helper.setTo(message.to());
            helper.setSubject(message.subject());
            helper.setText(message.text());
            helper.addAttachment(message.attachment().name(), () -> message.attachment().resource());

            sender.send(mimeMessage);
        } catch (MessagingException e) {
            log.debug("While preparation of mime-message the error occurred.");
            log.debug("Mime-message: {}", mimeMessage);
            throw new MailSenderException("Something when wrong while preparing the mime-message.", e);
        } catch (MailException e) {
            log.debug("While sending the mail with the mime-message the error occurred.");
            log.debug("Mime-message: {}", mimeMessage);
            throw new MailSenderException("Something when wrong while sending the mime-message.", e);
        }

    }

    @Override
    public void notifyAboutShipment(WarehouseKeeper keeper, WarehouseShipmentDto warehouseShipmentDto, String productName) {
        String receiver = keeper.getEmail();
        String subject = String.format("Product shipment from Warehouse #%d", keeper.getWarehouse().getId());
        String message = String.format(
                "Hello, %s!\nOn %s\nProduct %s in the amount of %d was shipped from warehouse #%d.",
                keeper.getName(),
                new Date(),
                productName,
                warehouseShipmentDto.getProductAmount(),
                keeper.getWarehouse().getId()
        );
        EmailMessage emailMessage = new EmailMessage(SENDER, receiver, subject, message);
        send(emailMessage);
    }

    @Override
    public void notifyAboutLowStock(WarehouseKeeper keeper, WarehouseItem item) {
        String receiver = keeper.getEmail();
        String subject = String.format("Stock of products are low at Warehouse #%d", keeper.getWarehouse().getId());
        String message = String.format(
                "Hello, %s!\nStock of product %s is running low! Only %d of named product is left at warehouse #%d.",
                keeper.getName(),
                item.getProduct().getName(),
                item.getAmount(),
                keeper.getWarehouse().getId()
        );
        EmailMessage emailMessage = new EmailMessage(SENDER, receiver, subject, message);
        System.out.println(message);
        send(emailMessage);
    }


}
