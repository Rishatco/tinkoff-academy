package ru.tinkoff.handymanService.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.tinkoff.handymanService.account.Account;
import ru.tinkoff.handymanService.account.AccountDTO;
import ru.tinkoff.handymanService.account.AccountService;
import ru.tinkoff.handymanService.user.skill.Skill;
import ru.tinkoff.handymanService.user.skill.SkillRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HandymanUserService {

    private final HandymanUserRepository handymanUserRepository;
    private final AccountService accountService;
    private final SkillRepository skillRepository;

    public void delete(Long id) {
        handymanUserRepository.deleteById(id);
    }

    public HandymanUser getById(Long id) {
        return handymanUserRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                "cannot find handymanUser with id=" + id));
    }

    public List<HandymanUser> getAll() {
        return handymanUserRepository.findAll();
    }

    public HandymanUser create(HandymanUserDTO handymanUserDTO) {
        HandymanUser handymanUser = new HandymanUser();
        handymanUser.setEmail(handymanUserDTO.getEmail());
        handymanUser.setFirstName(handymanUserDTO.getFirstName());
        handymanUser.setLastName(handymanUserDTO.getLastName());
        handymanUser.setPhone(handymanUserDTO.getPhone());

        handymanUser = handymanUserRepository.save(handymanUser);

        handymanUser.setAccounts(mapAccountDTOtoAccount(handymanUserDTO.getAccounts(), handymanUser.getId()));

        List<Skill> userSkills = createSkills(handymanUserDTO, handymanUser);
        handymanUser.setSkills(userSkills);

        return handymanUserRepository.save(handymanUser);
    }

    public HandymanUser update(HandymanUserDTO handymanUserDTO, Long id) {
        HandymanUser handymanUser = getById(id);

        handymanUser.setPhone(handymanUserDTO.getPhone());
        handymanUser.setFirstName(handymanUserDTO.getFirstName());
        handymanUser.setLastName(handymanUserDTO.getLastName());
        handymanUser.setEmail(handymanUserDTO.getEmail());

        return handymanUserRepository.save(handymanUser);
    }

    public HandymanUser setPhoto(Long id, MultipartFile photo) {
        HandymanUser handymanUser = getById(id);
        try {
            handymanUser.setPhoto(photo.getBytes());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "cannot load photo to user with id=" + id);
        }
        return handymanUserRepository.save(handymanUser);
    }


    public List<Account> mapAccountDTOtoAccount(List<AccountDTO> accountDTOs, Long HandymanUserId) {
        List<Account> accounts = new ArrayList<>();
        for (AccountDTO accountDTO : accountDTOs) {
            Account account = accountService.create(accountDTO, HandymanUserId);
            accounts.add(account);
        }
        return accounts;
    }

    public List<Skill> createSkills(HandymanUserDTO handymanUserDTO, HandymanUser handymanUser) {
        List<Skill> skills = new ArrayList<>();
        for (String skillName : handymanUserDTO.getSkills()) {
            Skill skill = new Skill();
            skill.setHandymanUser(handymanUser);
            skill.setName(skillName);
            skill = skillRepository.save(skill);
            skills.add(skill);
        }
        return skills;
    }

}
