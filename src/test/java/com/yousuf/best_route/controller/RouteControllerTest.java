package com.yousuf.best_route.controller;

import com.yousuf.best_route.constant.Ports;
import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.service.RouteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RouteControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    @Test
    @DisplayName("Should return the fastest route")
    void fastestRoute() {
        RouteDto routeDto = new RouteDto();
        routeDto.setFromPort(Ports.DEHAM.name());
        routeDto.setToPort(Ports.DEBRV.name());

        when(routeService.getFastestRoute(Ports.DEHAM.name(),Ports.DEBRV.name())).thenReturn(routeDto);

        ResponseEntity<RouteDto> routeDtoResponse = routeController.fastestRoute(Ports.DEHAM, Ports.DEBRV);
        assertThat(routeDtoResponse.getBody()).isEqualTo(routeDto);
        assertThat(routeDtoResponse.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return the safest route")
    void safestRoute() {
        RouteDto routeDto = new RouteDto();
        routeDto.setFromPort(Ports.DEHAM.name());
        routeDto.setToPort(Ports.DEBRV.name());

        when(routeService.getSafestRoute(Ports.DEHAM.name(),Ports.DEBRV.name())).thenReturn(routeDto);

        ResponseEntity<RouteDto> routeDtoResponse = routeController.safestRoute(Ports.DEHAM, Ports.DEBRV);
        assertThat(routeDtoResponse.getBody()).isEqualTo(routeDto);
        assertThat(routeDtoResponse.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }
}