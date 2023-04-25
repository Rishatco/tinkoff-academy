package ru.tinkoff.rancherService.landscape.Client;

import lombok.Getter;
import lombok.Setter;

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
