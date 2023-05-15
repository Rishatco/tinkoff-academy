package ru.tinkoff.landscapeService.client_type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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