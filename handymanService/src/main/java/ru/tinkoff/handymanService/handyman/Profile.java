package ru.tinkoff.handymanService.handyman;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.tinkoff.handymanService.landscape.сlient.Client;

@Data
@AllArgsConstructor
public class Profile {
    Handyman handyman;
    Client client;
}
