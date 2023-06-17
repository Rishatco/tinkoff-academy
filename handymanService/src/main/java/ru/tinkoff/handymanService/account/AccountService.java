package ru.tinkoff.handymanService.account;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.handymanService.user.HandymanUser;
import ru.tinkoff.handymanService.user.HandymanUserRepository;
import ru.tinkoff.handymanService.user.HandymanUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final HandymanUserRepository handymanUserRepository;

    public Account create(AccountDTO accountDTO, Long HandymanUserId) {
        HandymanUser handymanUser = handymanUserRepository.findById(HandymanUserId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "cannot find handymanUser with id=" + HandymanUserId));

        Account account = new Account();
        account.setCardNumber(accountDTO.getCardNumber());
        account.setHandymanUser(handymanUser);
        account.setPaymentSystem(accountDTO.getPaymentSystem());

        return accountRepository.save(account);
    }

    public Account getById(Long id) {
        return accountRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "cannot find account with id=" + id));
    }

    public void delete(Long id) {
        accountRepository.deleteById(id);
    }

    public List<Account> getAll() {
        return accountRepository.findAll();
    }

    public Account update(Long id, AccountDTO accountDTO) {
        Account account = getById(id);

        account.setPaymentSystem(accountDTO.getPaymentSystem());
        account.setCardNumber(accountDTO.getCardNumber());

        return accountRepository.save(account);
    }
}
