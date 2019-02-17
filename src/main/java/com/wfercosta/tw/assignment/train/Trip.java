package com.wfercosta.tw.assignment.train;

import java.util.Collections;
import java.util.List;

public class Trip {

    private final List<Route> routes;

    public Trip(List<Route> routes) {
        this.routes = routes;
    }

    public static Trip of(List<Route> routes) {
        return new Trip(routes);
    }

    public Route shortestRoute() {
        return this.routes.get(0);
    }

    public List<Route> routes() {
        return Collections.unmodifiableList(routes);
    }
}
