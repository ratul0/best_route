package com.yousuf.best_route.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity
@Table(name = "route")
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class Route {
    @Id
    @GeneratedValue
    private Long id;
    private String shipId;
    private int fromSeq;
    private int toSeq;
    private String fromPort;
    private String toPort;
    private Long duration;
    private int count;
    @OneToMany(mappedBy = "route", cascade = CascadeType.ALL)
    private List<Point> points;


}
