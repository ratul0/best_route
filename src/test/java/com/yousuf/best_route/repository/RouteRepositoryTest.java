package com.yousuf.best_route.repository;

import com.yousuf.best_route.entity.Route;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class RouteRepositoryTest {

    @Autowired
    private RouteRepository routeRepository;

    @BeforeEach
    void setUp() {
        createRoutes();
    }

    @AfterEach
    void tearDown() {
        routeRepository.deleteAll();
    }

    @Test
    @DisplayName("Should return the fastest route")
    void findTop1ByFromPortAndToPortOrderByDurationAsc() {
        Route route = routeRepository.findTop1ByFromPortAndToPortOrderByDurationAsc("A", "B");

        assertThat(route.getShipId()).isEqualTo("ship1");
    }

    @Test
    @DisplayName("Should return the safest route")
    void findTop1ByFromPortAndToPortOrderByCountDesc() {
        Route route = routeRepository.findTop1ByFromPortAndToPortOrderByCountDesc("A", "B");

        assertThat(route.getShipId()).isEqualTo("ship2");
    }

    @Test
    @DisplayName("Should return distinct from ports")
    void findDistinctFromPort() {
        List<String> fromPorts = routeRepository.findDistinctFromPort();

        assertThat(fromPorts).containsExactlyInAnyOrder("A");
    }

    @Test
    @DisplayName("Should return distinct to ports")
    void findDistinctToPort() {
        List<String> fromPorts = routeRepository.findDistinctToPort();

        assertThat(fromPorts).containsExactlyInAnyOrder( "B");
    }

    private void createRoutes(){
        Route route1 = new Route();
        route1.setShipId("ship1");
        route1.setFromSeq(1);
        route1.setToSeq(2);
        route1.setFromPort("A");
        route1.setToPort("B");
        route1.setDuration(100L);
        route1.setCount(3);

        Route route2 = new Route();
        route2.setShipId("ship2");
        route2.setFromSeq(1);
        route2.setToSeq(2);
        route2.setFromPort("A");
        route2.setToPort("B");
        route2.setDuration(200L);
        route2.setCount(5);

        routeRepository.saveAll(List.of(route1, route2));
    }
}