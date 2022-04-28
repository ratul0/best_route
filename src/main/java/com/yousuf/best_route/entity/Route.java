package com.yousuf.best_route.entity;

import lombok.*;

import javax.persistence.*;


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
    @Column(columnDefinition="TEXT")
    private String points;
}
