package com.wfercosta.tw.assignment.train;



import java.util.*;

public class RoutePath {

    private List<String> paths;
    private Map<RouteFilterType, Integer> filters = new HashMap<>();

    public RoutePath(List<String> paths, Map<RouteFilterType, Integer> filters) {
        this.paths = Collections.unmodifiableList(paths);
        this.filters.putAll(filters);
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

    public Map<RouteFilterType, Integer> filters() {
        return this.filters;
    }


    public List<String> paths() {
        return Collections.unmodifiableList(this.paths);
    }

    public static class RoutePathBuilder {

        private List<String> paths = new ArrayList<>();
        private Map<RouteFilterType, Integer> filters = new HashMap<>();

        public RoutePathBuilder to(String node) {
            this.paths.add(node);
            return this;
        }

        public RoutePath end() {
            return new RoutePath(this.paths, filters);
        }


        public RoutePathBuilder filter(RouteFilterType type, int required) {
            filters.put(type, required);
            return this;
        }
    }


}


