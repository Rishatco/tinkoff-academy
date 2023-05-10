package ru.tinkoff.handymanService.handyman;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class HandymanDTO {
    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
