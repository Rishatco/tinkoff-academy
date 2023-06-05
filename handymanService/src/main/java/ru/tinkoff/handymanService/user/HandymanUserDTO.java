package ru.tinkoff.handymanService.user;

import lombok.Data;
import ru.tinkoff.handymanService.account.AccountDTO;

import java.util.List;

@Data
public class HandymanUserDTO {
    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private List<AccountDTO> accounts;

    private List<String> skills;
}
