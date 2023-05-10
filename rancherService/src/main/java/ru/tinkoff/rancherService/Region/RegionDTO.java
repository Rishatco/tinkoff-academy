package ru.tinkoff.rancherService.Region;

import lombok.Data;

import java.util.List;

@Data
public class RegionDTO {

    private String login;
    private String email;
    private String phone;
    private Double latitude;
    private Double longitude;
    private Double area;
    private List<String> jobs;
}