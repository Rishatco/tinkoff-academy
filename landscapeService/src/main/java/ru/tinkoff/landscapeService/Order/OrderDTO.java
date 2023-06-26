package ru.tinkoff.landscapeService.Order;

import lombok.Data;

import java.util.UUID;

@Data
public class OrderDTO {

    private WorkType workType;
    private WorkStatus workStatus;
    private Long fieldId;
    private UUID userId;
}
