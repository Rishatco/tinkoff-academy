package ru.tinkoff.handymanService.account;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public List<Account> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public Account getById(@PathVariable Long id) {
        return accountService.getById(id);
    }

    @PostMapping("/{handymanUserId}")
    public Account create(@RequestBody AccountDTO accountDTO, @PathVariable Long handymanUserId) {
        return accountService.create(accountDTO, handymanUserId);
    }

    @PutMapping("/{id}")
    public Account update(@RequestBody AccountDTO accountDTO, @PathVariable Long id) {
        return accountService.update(id, accountDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
