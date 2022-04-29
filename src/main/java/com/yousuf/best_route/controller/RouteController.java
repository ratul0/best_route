package com.yousuf.best_route.controller;

import com.yousuf.best_route.constant.Ports;
import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/fastest-route/{from}/{to}")
    public RouteDto fastestRoute(@PathVariable("from") Ports from, @PathVariable("to") Ports to) {
        return this.routeService.getFastestRoute(from.name(), to.name());
    }

    @GetMapping("/safest-route/{from}/{to}")
    public RouteDto safestRoute(@PathVariable("from") Ports from, @PathVariable("to") Ports to) {
        return this.routeService.getSafestRoute(from.name(),to.name());
    }


}
