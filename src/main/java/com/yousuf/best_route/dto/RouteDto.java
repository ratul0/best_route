package com.yousuf.best_route.dto;

import lombok.Data;

@Data
public class RouteDto {
    private Long id;
    private String fromPort;
    private String toPort;
    private String points;
}
