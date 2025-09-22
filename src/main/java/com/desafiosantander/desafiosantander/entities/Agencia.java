package com.desafiosantander.desafiosantander.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "agencia", schema = "santander")
public class Agencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "nome_agencia")
    private String nome;

    @Column(name = "posX")
    private Double posX;

    @Column(name = "posY")
    private Double posY;

    public Agencia(Double posX, Double posY) {
        this.posX = posX;
        this.posY = posY;
    }


}
