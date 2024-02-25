package com.example.sw.domain;

import static com.example.sw.common.PlanetConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;
// aqui só vai passar apenas as class que eu quero que seja carregada
// @SpringBootTest(classes = PlanetService.class)

//@SpringBootTest(classes = PlanetService.class)
@ExtendWith(MockitoExtension.class)
public class PlanetServiceTest {
    //@Autowired
    @InjectMocks // Instancia o planetService
    private PlanetService planetService;

    //@MockBean
    @Mock
    private PlanetRepository planetRepository;


    @Test
    // operacao_estado_returno
    public void createPlanet_WithValidData_returnsPlanet() {
        when(planetRepository.save(PLANET)).thenReturn(PLANET);// when quando //thenReturn então retornar

        Planet sut = planetService.create(PLANET);

        assertThat(sut).isEqualTo(PLANET);
    }

    @Test
    // operacao_estado_returno
    public void createPlanet_WithInvalidData_throwsException() {
        when(planetRepository.save(INVALID_PLANET)).thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> planetService.create(INVALID_PLANET)).isInstanceOf(RuntimeException.class);
    }
    @Test
    public void getPlanet_ByExistingId_returnsPlanet() {
        // AAA - ARANGE, ACT, E ASSERT
        when(planetRepository.findById(anyLong())).thenReturn(Optional.of(PLANET));

        Optional<Planet> sut = planetService.get(1L);

        assertThat(sut).isNotEmpty();
        assertThat(sut.get()).isEqualTo(PLANET);
    }
    @Test
    public void getPlanet_ByUnexistingId_returnsEmpty() {
        when(planetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Optional<Planet> sut = planetService.get(1L);

        assertThat(sut).isEmpty();
    }
}
