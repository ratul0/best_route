package com.yousuf.best_route.controller;

import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @GetMapping("/best-route")
    public RouteDto bestRoute() {
        return this.routeService.getBestRoute();
    }
}
