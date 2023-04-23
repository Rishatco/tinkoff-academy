package ru.tinkoff.handymanService.handyman;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Document
public class Handyman {
    @Id
    private String id;
    private UUID subId;
    private Double latitude;
    private Double longitude;
    private List<String> skills;
}
