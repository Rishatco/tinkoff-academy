package ru.tinkoff.landscapeService.client;

import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
public class ClientDTO {

    private String login;
    private String email;
    private String phone;
    private Long clientType;
    private Double latitude;
    private Double longitude;
}
