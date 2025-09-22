package com.desafiosantander.desafiosantander.services;

import com.desafiosantander.desafiosantander.entities.Agencia;
import com.desafiosantander.desafiosantander.entities.dto.AgenciaRequestDTO;
import com.desafiosantander.desafiosantander.entities.dto.AgenciaResponseDTO;
import com.desafiosantander.desafiosantander.exceptions.RegraDeNegocioException;
import com.desafiosantander.desafiosantander.repositories.AgenciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.LinkedHashMap;

@Service
public class AgenciaService {

    @Autowired
    private AgenciaRepository repository;

    public AgenciaService(AgenciaRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Agencia cadastrar(AgenciaRequestDTO dto) {

        Agencia agencia = new Agencia(dto.getPosX(), dto.getPosY());
        if(agencia.getPosY() == null || agencia.getPosX() == null){
            throw new RegraDeNegocioException("Campo faltante! A requisição deve conter os campos: posX e posY.");
        }
        Agencia saved = repository.save(agencia);


        Agencia response = new Agencia();
        response.setNome("AGENCIA_" + saved.getId());
        response.setPosX(saved.getPosX());
        response.setPosY(saved.getPosY());

        return response;
    }

    @Cacheable(value = "distancias", key = "#usuarioX + '_' + #usuarioY")
    public Map<String, String> calcularDistancias(Double usuarioX, Double usuarioY) {
        System.out.println("Calculando distância sem cache...");
        List<Agencia> agencias = repository.findAll();


        return agencias.stream()
                .sorted(Comparator.comparingDouble(
                        a -> distancia(usuarioX, usuarioY, a.getPosX(), a.getPosY())
                ))
                .collect(Collectors.toMap(
                        a -> "AGENCIA_" + a.getId(),
                        a -> "distacia = " + String.valueOf(distancia(usuarioX, usuarioY, a.getPosX(), a.getPosY())),
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }


    private double distancia(double x1, double y1, double x2, double y2) {
        double distancia = Math.hypot(x2 - x1, y2 - y1);
        return Math.round(distancia * 100.0) / 100.0;
    }


}
