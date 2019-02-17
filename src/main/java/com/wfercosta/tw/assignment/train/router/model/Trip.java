package com.wfercosta.tw.assignment.train.router.model;


import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Trip {

    private final List<Route> routes;
    private final Route selected;
    private final Route shortest;

    public Trip(List<Route> routes) {
        this(routes, null);
    }

    public Trip(List<Route> routes, Route selected) {
        this.routes = routes;
        this.shortest = this.routes.get(0);

        if (Objects.isNull(selected)) {
            this.selected = shortest;
        } else {
            this.selected = selected;
        }

    }

    public static Trip of(List<Route> routes, Route match) {
        return new Trip(routes, match);
    }

    public Route shortestRoute() {
        return shortest;
    }

    public List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }

    public Route selected() {
        return this.selected;
    }
}
