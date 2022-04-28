package com.yousuf.best_route.entity;

import lombok.Data;

@Data
public class Route {
    private String shipId;
    private int fromSeq;
    private int toSeq;
    private String fromPort;
    private String toPort;
    private Long duration;
    private int count;
}
