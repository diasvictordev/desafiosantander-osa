package com.desafiosantander.desafiosantander.controllers;

import com.desafiosantander.desafiosantander.entities.Agencia;
import com.desafiosantander.desafiosantander.entities.dto.AgenciaRequestDTO;
import com.desafiosantander.desafiosantander.entities.dto.AgenciaResponseDTO;
import com.desafiosantander.desafiosantander.exceptions.RegraDeNegocioException;
import com.desafiosantander.desafiosantander.services.AgenciaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/desafio")
public class AgenciaController {

    @Autowired
    private AgenciaService service;


    @PostMapping("/cadastrar")
    public ResponseEntity<Agencia> cadastrar(@RequestBody AgenciaRequestDTO dto) {
        var saved = service.cadastrar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/distancia")
    public ResponseEntity<Map<String, String>> distancia(
            @RequestParam Double posX,
            @RequestParam Double posY) {
        Map<String, String> resultado = service.calcularDistancias(posX, posY);
        return ResponseEntity.ok(resultado);
    }


    @ExceptionHandler(RegraDeNegocioException.class)
    public ResponseEntity<Map<String, String>> handleRegraDeNegocio(RegraDeNegocioException ex) {
        Map<String, String> error = Map.of("Erro", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

}

