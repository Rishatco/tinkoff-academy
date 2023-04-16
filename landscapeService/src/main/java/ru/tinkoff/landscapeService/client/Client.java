package ru.tinkoff.landscapeService.client;

import lombok.*;


import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private UUID id;

    @Column(name = "login")
    private String login;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    @Column(name = "client_type_v2")
    private ClientType clientType;

    @Column(name = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "updatingTime")
    private LocalDateTime updatingTime;


    @PrePersist
    public void prePersist(){
        this.creationDate = LocalDateTime.now();
        this.updatingTime = this.creationDate;
    }

    @PreUpdate
    public void PreUpdate(){
        this.updatingTime = LocalDateTime.now();
    }
}
