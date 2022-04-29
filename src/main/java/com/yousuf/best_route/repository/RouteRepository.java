package com.yousuf.best_route.repository;

import com.yousuf.best_route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends JpaRepository<Route, Long> {
    public Route findTop1ByFromPortAndToPortOrderByDurationAsc(String fromPort,String toPort);
    public Route findTop1ByFromPortAndToPortOrderByCountDesc(String fromPort,String toPort);

    @Query("SELECT DISTINCT fromPort FROM Route ")
    public List<String> findDistinctFromPort();

    @Query("SELECT DISTINCT toPort FROM Route ")
    public List<String> findDistinctToPort();

}

