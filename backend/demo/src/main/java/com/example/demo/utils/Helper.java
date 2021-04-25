package com.example.demo.utils;

import com.example.demo.VO.ProductVO;
import com.example.demo.VO.ProposalVO;
import com.example.demo.exceptions.ResponseException;
import com.example.demo.model.AuditEntity;
import com.example.demo.model.Picture;
import org.springframework.http.HttpStatus;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Helper {
    public static void auditSave(Object o) {
        AuditEntity auditEntity = (AuditEntity) o;
        auditEntity.setCreatedWhen(LocalDateTime.now());
        auditEntity.setUpdatedWhen(LocalDateTime.now());
    }

    public static void auditUpdate(Object o) {
        AuditEntity auditEntity = (AuditEntity) o;
        auditEntity.setUpdatedWhen(LocalDateTime.now());
    }

    public static void base64ToPicture(String base64, Picture picture) { // TODO Сделать универсалку
        try {
            String[] formatBase64 = base64.split(",", 2);
            String typeBase64 = formatBase64[0].replace("data:", "")
                    .replace(";base64", "");
            String docBase64 = formatBase64[1];
            BASE64Decoder decoder = new BASE64Decoder();
            picture.setContent(decoder.decodeBuffer(docBase64));
            picture.setContentType(typeBase64);
        } catch (ResponseException e) {
            throw e;
        } catch (IOException e) {
            throw new ResponseException(HttpStatus.BAD_REQUEST, "Не удалось прочитать base64 file");
        }
    }

    public static String proposalToMessageForCreate(ProposalVO proposal) {
        return "Поступила новая заявка! \n" +
                "Уникальный номер заявки: " + proposal.getId() + "\n" +
                "Заказчик: " + proposal.getCustomer() + "\n" +
                "Адрес доставки: " + proposal.getAddress() + "\n" +
                "Контактные данные: " + proposal.getContact() + "\n" +
                "Комментарий заказчика: " + proposal.getComment() + "\n" +
                "Статус: " + proposal.getStatus().getNameRu() + "\n" +
                "Дата и время создания: " + proposal.getCreatedWhen().format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")) + "\n" + "\n" +
                "Состав заказа: " + "\n" + "\n" + productsToMessageForCreate(proposal.getProducts());
    }

    public static String productsToMessageForCreate(List<ProductVO> products) {
        String result = "";
        for (int i = 0; i < products.size(); i++) {
            ProductVO productVO = products.get(i);
            result +=
                    (i + 1) + "." + "\n"
                            + "Название товара : " + productVO.getName() + "\n"
                            + "Цена : " + productVO.getName() + "\n"
                            + "Количество : " + productVO.getQuantityForProposal() + "\n"
                            + "Осталось на складе : " + productVO.getQuantity() + "\n"
                            + "Артикул : " +productVO.getArticle() + "\n" + "\n";
        }
        return result;
    }
}
