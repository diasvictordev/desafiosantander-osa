package com.desafiosantander.desafiosantander.entities.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AgenciaRequestDTO {
    private Double posX;
    private Double posY;
}
