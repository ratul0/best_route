package com.yousuf.best_route.dto;

import lombok.Data;

import java.util.List;

@Data
public class RouteDto {
    private String fromPort;
    private String toPort;
    private Long duration;
    List<PointDto> points;
}
