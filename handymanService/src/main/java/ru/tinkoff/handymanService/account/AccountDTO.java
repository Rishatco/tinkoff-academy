package ru.tinkoff.handymanService.account;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;


@Data
public class AccountDTO {

    private String cardNumber;

    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;
}
