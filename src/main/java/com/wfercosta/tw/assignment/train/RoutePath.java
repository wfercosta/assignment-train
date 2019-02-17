package com.wfercosta.tw.assignment.train;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoutePath {

    private int maxDistance = -1;
    private List<String> paths;
    private int index = 0;
    private int maxStops = -1;

    public RoutePath(List<String> paths, int maxStops, int maxDistance) {
        this.paths = Collections.unmodifiableList(paths);
        this.maxStops = maxStops;
        this.maxDistance = maxDistance;
    }

    @Override
    public String toString() {
        return "RoutePath: [paths= "+ this.paths + "]";
    }

    public static RoutePathBuilder startAt(String node) {
        return new RoutePathBuilder().to(node);
    }

    public String start() {
        return this.paths.get(0);
    }

    public String end() {
        return this.paths.get(this.paths.size() - 1);
    }

    public int maxStops() {
        return this.maxStops;
    }

    public int maxDistance() {
        return this.maxDistance;
    }

    public List<String> paths() {
        return Collections.unmodifiableList(this.paths);
    }

    public static class RoutePathBuilder {

        private List<String> paths = new ArrayList<>();
        private String from;
        private int maxStops = -1;
        private int maxDistance = -1;

        public RoutePathBuilder to(String node) {
            this.paths.add(node);
            return this;
        }

        public RoutePath end() {
            return new RoutePath(this.paths, maxStops, maxDistance);
        }

        public RoutePathBuilder stopsLessThanEqualTo(int maxStops) {
            this.maxStops = maxStops;
            return this;
        }

        public RoutePathBuilder distanceLessThanEqualTo(int distance) {
            this.maxDistance = distance;
            return this;
        }
    }


}


