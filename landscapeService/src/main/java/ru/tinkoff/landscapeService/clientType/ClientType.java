package ru.tinkoff.landscapeService.clientType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "client_type_v2")
public class ClientType{

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "typename")
    private String name;
}