package ru.tinkoff.handymanService.landscape.сlient;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
public class Client {

    private UUID id;
    private String login;
    private String email;
    private String phone;
    private ClientType clientType;
    private LocalDateTime creationDate;
    private LocalDateTime updatingTime;
    private Double latitude;
    private Double longitude;

}
