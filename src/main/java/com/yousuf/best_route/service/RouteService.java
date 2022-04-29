package com.yousuf.best_route.service;

import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.entity.Route;
import com.yousuf.best_route.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteDto getFastestRoute(String from, String to) {
        Route route = routeRepository.findTop1ByFromPortAndToPortOrderByDurationAsc(from,to);
        return convertToDto(route);
    }

    public RouteDto getSafestRoute(String from, String to) {
        Route route = routeRepository.findTop1ByFromPortAndToPortOrderByCountDesc(from,to);
        return convertToDto(route);
    }

    public List<String> getFromPorts() {
        return routeRepository.findDistinctFromPort();
    }

    public List<String> getToPorts() {
        return routeRepository.findDistinctToPort();
    }

    private RouteDto convertToDto(Route route) {
        return modelMapper.map(route, RouteDto.class);
    }

    private Route convertToEntity(RouteDto routeDto) {
        return modelMapper.map(routeDto, Route.class);
    }

}
