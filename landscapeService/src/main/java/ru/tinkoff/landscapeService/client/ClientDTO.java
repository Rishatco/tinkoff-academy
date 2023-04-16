package ru.tinkoff.landscapeService.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {

    private String login;
    private String email;
    private String phone;
    private Integer clientType;
}
