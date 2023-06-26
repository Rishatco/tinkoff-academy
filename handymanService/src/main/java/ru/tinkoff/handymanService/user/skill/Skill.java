package ru.tinkoff.handymanService.user.skill;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.tinkoff.handymanService.user.HandymanUser;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Table(name = "skill")
@Getter
@Setter
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "handyman_user_id")
    private HandymanUser handymanUser;

}
