package com.yousuf.best_route.service;

import com.yousuf.best_route.constant.PortName;
import com.yousuf.best_route.constant.Ports;
import com.yousuf.best_route.dto.RouteDto;
import com.yousuf.best_route.entity.Route;
import com.yousuf.best_route.exception.InvalidPortNameException;
import com.yousuf.best_route.exception.RouteNotFoundException;
import com.yousuf.best_route.repository.RouteRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class RouteService {
    private final RouteRepository routeRepository;
    private final ModelMapper modelMapper;

    public RouteDto getFastestRoute(String from, String to) {
        return Optional.ofNullable(routeRepository.findTop1ByFromPortAndToPortOrderByDurationAsc(from,to))
                .map(this::convertToDto)
                .orElseThrow(() -> new RouteNotFoundException("No Route found from " + from + " to " + to));
    }

    public RouteDto getSafestRoute(String from, String to) {
        return  Optional.ofNullable(routeRepository.findTop1ByFromPortAndToPortOrderByCountDesc(from,to))
                .map(this::convertToDto)
                .orElseThrow(() -> new RouteNotFoundException("No Route found from " + from + " to " + to));
    }

    public HashMap<Ports, String> getFromPorts() throws InvalidPortNameException {
        HashMap<Ports, String> portMap = new HashMap<>();
        List<String> fromPorts =  routeRepository.findDistinctFromPort();
        return getPorts(portMap, fromPorts);
    }

    public HashMap<Ports, String> getToPorts() throws InvalidPortNameException {
        HashMap<Ports, String> portMap = new HashMap<>();
        List<String> fromPorts =  routeRepository.findDistinctToPort();
        return getPorts(portMap, fromPorts);
    }

    private RouteDto convertToDto(Route route) {
        return modelMapper.map(route, RouteDto.class);
    }

    private Route convertToEntity(RouteDto routeDto) {
        return modelMapper.map(routeDto, Route.class);
    }

    private HashMap<Ports, String> getPorts(HashMap<Ports, String> ports, List<String> fromPorts) throws InvalidPortNameException {
        for (String portCode : fromPorts) {
            if(PortName.LISTOFNAMES.get(Ports.valueOf(portCode))==null) {
                throw new InvalidPortNameException("Invalid Port Name");
            }
            ports.put(Ports.valueOf(portCode), PortName.LISTOFNAMES.get(Ports.valueOf(portCode)));
        }
        return ports;
    }

}
