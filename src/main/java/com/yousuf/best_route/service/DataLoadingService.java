package com.yousuf.best_route.service;

import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.yousuf.best_route.entity.Route;
import com.yousuf.best_route.repository.RouteRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class DataLoadingService {
    private final RouteRepository routeRepository;

    @EventListener(ApplicationStartedEvent.class)
    public void eventListener() {
        loadData();
    }


    public void loadData() {
        try{
            log.info("Loading data...");
            List<Route> routes = new ArrayList<>();

            Resource resource = new ClassPathResource("routes.csv");
            InputStream inputStream = resource.getInputStream();
            CsvParserSettings settings = new CsvParserSettings();
            settings.setMaxCharsPerColumn(1000000);
            settings.setHeaderExtractionEnabled(true);
            CsvParser parser = new CsvParser(settings);
            List<Record> parseAllRecords = parser.parseAllRecords(inputStream);

            parseAllRecords.forEach(record -> {
                Route route = new Route();
                route.setShipId(record.getString("id"));
                route.setFromSeq(record.getInt("from_seq"));
                route.setToSeq(record.getInt("to_seq"));
                route.setFromPort(record.getString("from_port"));
                route.setToPort(record.getString("to_port"));
                route.setDuration(record.getLong("leg_duration"));
                route.setCount(record.getInt("count"));
                route.setPoints(record.getString("points"));
                routes.add(route);
            });
            routeRepository.saveAll(routes);
        } catch (Exception e) {
            log.error("Error while loading data", e);
        }

    }
}
