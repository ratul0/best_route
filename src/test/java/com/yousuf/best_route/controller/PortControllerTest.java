package com.yousuf.best_route.controller;

import com.yousuf.best_route.constant.Ports;
import com.yousuf.best_route.service.RouteService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PortControllerTest {

    @Mock
    private RouteService routeService;

    @InjectMocks
    private PortController portController;

    @Test
    @DisplayName("Should return all departure ports")
    void fromPorts() {
        HashMap<Ports,String> ports = new HashMap<>();
        ports.put(Ports.DEBRV,"Bremerhaven");
        ports.put(Ports.DEHAM,"Hamburg");

        when(routeService.getFromPorts()).thenReturn(ports);

        ResponseEntity<HashMap<Ports, String>> fromPortsResponse = portController.fromPorts();
        assertThat(fromPortsResponse.getBody()).isEqualTo(ports);
        assertThat(fromPortsResponse.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Should return all destination ports")
    void toPorts() {
        HashMap<Ports,String> ports = new HashMap<>();
        ports.put(Ports.DEHAM,"Hamburg");

        when(routeService.getToPorts()).thenReturn(ports);

        assertThat(portController.toPorts().getBody()).isEqualTo(ports);
        assertThat(portController.toPorts().getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }
}