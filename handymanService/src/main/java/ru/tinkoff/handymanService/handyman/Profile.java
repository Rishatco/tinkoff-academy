package ru.tinkoff.handymanService.handyman;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tinkoff.handymanService.landscape.Client.Client;

@Data
@AllArgsConstructor
public class Profile {
    Handyman handyman;
    Client client;
}
