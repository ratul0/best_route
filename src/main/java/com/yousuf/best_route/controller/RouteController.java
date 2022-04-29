package com.yousuf.best_route.controller;

import com.yousuf.best_route.constant.Ports;
import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@AllArgsConstructor
public class RouteController {

    private final RouteService routeService;

    @Operation(summary = "Get the fastest route from one port to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fastest route with specific route details", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
            @ApiResponse(responseCode = "404", description = "Route not found", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
            @ApiResponse(responseCode = "400", description = "Invalid port name", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")})
    })
    @GetMapping("/fastest-route/{from}/{to}")
    public RouteDto fastestRoute(@PathVariable("from") Ports from, @PathVariable("to") Ports to) {
        return this.routeService.getFastestRoute(from.name(), to.name());
    }


    @Operation(summary = "Get the safest route from one port to another")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Safest route with specific route details", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
            @ApiResponse(responseCode = "404", description = "Route not found", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
            @ApiResponse(responseCode = "400", description = "Invalid port name", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")})
    })
    @GetMapping("/safest-route/{from}/{to}")
    public RouteDto safestRoute(@PathVariable("from") Ports from, @PathVariable("to") Ports to) {
        return this.routeService.getSafestRoute(from.name(),to.name());
    }


}
