package com.yousuf.best_route.service;

import com.yousuf.best_route.entity.Route;
import com.yousuf.best_route.exception.InvalidPortNameException;
import com.yousuf.best_route.exception.RouteNotFoundException;
import com.yousuf.best_route.repository.RouteRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RouteServiceTest {

    @Mock private RouteRepository routeRepository;
    @Mock private ModelMapper modelMapper;
    private RouteService routeService;

    @BeforeEach
    void setUp() {
        routeService = new RouteService(routeRepository,modelMapper);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should throw a RouteNotFoundException when the route is not found")
    void shouldThrowExceptionWhenNoFastestRouteFound() {
        String fromPort = "A";
        String toPort = "B";

        assertThatThrownBy(() -> routeService.getFastestRoute(fromPort, toPort))
                .isInstanceOf(RouteNotFoundException.class)
                .hasMessageContaining("No Route found from " + fromPort + " to " + toPort);
    }

    @Test
    @DisplayName("Should throw a RouteNotFoundException when the route is not found")
    void shouldThrowExceptionWhenNoSafestRouteFound() {
        String fromPort = "A";
        String toPort = "B";

        assertThatThrownBy(() -> routeService.getSafestRoute(fromPort, toPort))
                .isInstanceOf(RouteNotFoundException.class)
                .hasMessageContaining("No Route found from " + fromPort + " to " + toPort);
    }

    @Test
    @DisplayName("Should return all distinct from ports")
    void getFromPorts() throws InvalidPortNameException {
        routeService.getFromPorts();

        verify(routeRepository).findDistinctFromPort();
    }

    @Test
    @DisplayName("Should return all distinct to ports")
    void getToPorts() throws InvalidPortNameException {
        routeService.getToPorts();

        verify(routeRepository).findDistinctToPort();
    }
}