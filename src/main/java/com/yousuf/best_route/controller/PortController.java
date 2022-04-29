package com.yousuf.best_route.controller;

import com.yousuf.best_route.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class PortController {
    private final RouteService routeService;

    @Operation(summary = "Get all departure ports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of departure ports code", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
    })
    @GetMapping("/from-ports")
    public List<String> fromPorts() {
        return this.routeService.getFromPorts();
    }

    @Operation(summary = "Get all destination ports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of destination ports code", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
    })
    @GetMapping("/to-ports")
    public List<String> toPorts() {
        return this.routeService.getToPorts();
    }
}
