package com.yousuf.best_route.service;

import com.google.gson.Gson;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import com.yousuf.best_route.entity.Point;
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
import java.util.Arrays;
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
                try{
                    Route route = new Route();
                    List<Point> points = new ArrayList<>();
                    String pointsString = record.getString("points");
                    String[][] parsedPoints = new Gson().fromJson(pointsString,String[][].class);


                    route.setPoints(points);
                    route.setShipId(record.getString("id"));
                    route.setFromSeq(record.getInt("from_seq"));
                    route.setToSeq(record.getInt("to_seq"));
                    route.setFromPort(record.getString("from_port"));
                    route.setToPort(record.getString("to_port"));
                    route.setDuration(record.getLong("leg_duration"));
                    route.setCount(record.getInt("count"));

                    for (String[] dataPoint: parsedPoints) {
                        Point point = new Point();
                        point.setLatitude(Double.parseDouble(dataPoint[1]));
                        point.setLongitude(Double.parseDouble(dataPoint[0]));
                        point.setRoute(route);
                        points.add(point);
                    }
                    routes.add(route);
                }catch (Exception e){
                    log.error("Error while parsing record: {}", record);
                }
            });
            routeRepository.saveAll(routes);
        } catch (Exception e) {
            log.error("Error while loading data", e);
        }

    }
}
