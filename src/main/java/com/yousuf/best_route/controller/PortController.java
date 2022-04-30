package com.yousuf.best_route.controller;

import com.yousuf.best_route.constant.PortName;
import com.yousuf.best_route.constant.Ports;
import com.yousuf.best_route.exception.InvalidPortNameException;
import com.yousuf.best_route.service.RouteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
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
    public ResponseEntity<HashMap<Ports, String>> fromPorts() throws InvalidPortNameException {
        return ResponseEntity.ok().body(routeService.getFromPorts());
    }

    @Operation(summary = "Get all destination ports")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of destination ports code", content = {@Content(mediaType="application/json"), @Content(mediaType="application/xml")}),
    })
    @GetMapping("/to-ports")
    public ResponseEntity<HashMap<Ports, String>> toPorts() throws InvalidPortNameException {
        return ResponseEntity.ok().body(routeService.getToPorts());
    }
}
