package com.yousuf.best_route.controller;

import com.yousuf.best_route.service.RouteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PortController {
    private final RouteService routeService;

    @GetMapping("/from-ports")
    public List<String> fromPorts() {
        return this.routeService.getFromPorts();
    }

    @GetMapping("/to-ports")
    public List<String> toPorts() {
        return this.routeService.getToPorts();
    }
}
