package ru.tinkoff.handymanService.user;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import ru.tinkoff.handymanService.account.Account;
import ru.tinkoff.handymanService.user.skill.Skill;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user")
public class HandymanUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone")
    private String phone;

    @Type(type="org.hibernate.type.BinaryType")
    @Column(name = "photo")
    private byte[] photo;

    @OneToMany(mappedBy = "handymanUser", cascade = CascadeType.REMOVE)
    private List<Account> accounts;

    @OneToMany(mappedBy = "handymanUser", cascade = CascadeType.REMOVE)
    private List<Skill> skills;
}
