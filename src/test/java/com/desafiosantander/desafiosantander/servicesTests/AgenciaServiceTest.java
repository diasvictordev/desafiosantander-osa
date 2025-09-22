package com.desafiosantander.desafiosantander.servicesTests;

import com.desafiosantander.desafiosantander.entities.Agencia;
import com.desafiosantander.desafiosantander.entities.dto.AgenciaRequestDTO;
import com.desafiosantander.desafiosantander.exceptions.RegraDeNegocioException;
import com.desafiosantander.desafiosantander.repositories.AgenciaRepository;
import com.desafiosantander.desafiosantander.services.AgenciaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AgenciaServiceTest {

    private AgenciaRepository repository;
    private AgenciaService service;

    @BeforeEach
    public void setUp() {
        repository = mock(AgenciaRepository.class);
        service = new AgenciaService(repository);
    }

    @Test
    public void deveCadastrarAgenciaComSucesso() {
        AgenciaRequestDTO dto = new AgenciaRequestDTO(10.0, 20.0);

        Agencia agenciaSalva = new Agencia(dto.getPosX(), dto.getPosY());
        agenciaSalva.setId(1L);

        when(repository.save(any(Agencia.class))).thenReturn(agenciaSalva);

        Agencia result = service.cadastrar(dto);

        assertEquals("AGENCIA_1", result.getNome());
        assertEquals(10.0, result.getPosX());
        assertEquals(20.0, result.getPosY());

        ArgumentCaptor<Agencia> captor = ArgumentCaptor.forClass(Agencia.class);
        verify(repository).save(captor.capture());
    }

    @Test
    public void deveLancarExcecaoSePosXouPosYForemNulos() {
        AgenciaRequestDTO dto = new AgenciaRequestDTO(null, 20.0);
        RegraDeNegocioException exception = assertThrows(
                RegraDeNegocioException.class,
                () -> service.cadastrar(dto)
        );
        assertTrue(exception.getMessage().contains("Campo faltante"));
    }

    @Test
    public void deveCalcularDistanciasCorretamente() {
        Agencia a1 = new Agencia(0.0, 0.0);
        a1.setId(1L);
        Agencia a2 = new Agencia(3.0, 4.0);
        a2.setId(2L);
        Agencia a3 = new Agencia(6.0, 8.0);
        a3.setId(3L);

        when(repository.findAll()).thenReturn(List.of(a3, a1, a2));
        Map<String, String> resultado = service.calcularDistancias(0.0, 0.0);


        assertEquals(3, resultado.size());

        List<String> chaves = resultado.keySet().stream().toList();

        assertEquals("AGENCIA_1", chaves.get(0));
        assertEquals("AGENCIA_2", chaves.get(1));
        assertEquals("AGENCIA_3", chaves.get(2));
    }
}
