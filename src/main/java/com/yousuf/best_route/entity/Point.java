package com.yousuf.best_route.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "points")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Setter
public class Point {
    @Id
    @GeneratedValue
    private Long id;
    private double longitude;
    private double latitude;
    @ManyToOne(fetch = FetchType.LAZY)
    private Route route;
}
