package com.yousuf.best_route.service;

import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.entity.Route;
import com.yousuf.best_route.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteDto getBestRoute() {
        Route route = routeRepository.findById(1L).orElse(null);
        return convertToDto(route);
    }

    private RouteDto convertToDto(Route route) {
        return modelMapper.map(route, RouteDto.class);
    }

    private Route convertToEntity(RouteDto routeDto) {
        return modelMapper.map(routeDto, Route.class);
    }

}
