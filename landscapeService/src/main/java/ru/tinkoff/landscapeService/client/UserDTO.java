package ru.tinkoff.landscapeService.client;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private String login;
    private String email;
    private String phone;
    private Long clientType;
    private Double latitude;
    private Double longitude;
}
