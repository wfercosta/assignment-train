package com.wfercosta.tw.assignment.train;



import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class RoutePath {

    private List<String> paths;
    private int index = 0;

    public RoutePath(List<String> paths) {
        this.paths = Collections.unmodifiableList(paths);
    }

    @Override
    public String toString() {
        return "RoutePath: [paths= "+ this.paths + "]";
    }

    public static RoutePathBuilder startAt(String node) {
        return new RoutePathBuilder().to(node);
    }

    public Optional<String> next() {

        if (this.paths.size() == this.index) {
            return Optional.empty();
        }

        return Optional.of(this.paths.get(this.index ++));
    }

    public String start() {
        return this.paths.get(0);
    }

    public String end() {
        return this.paths.get(this.paths.size() - 1);
    }

    public List<String> paths() {
        return Collections.unmodifiableList(this.paths);
    }

    public static class RoutePathBuilder {

        private List<String> paths = new ArrayList<>();
        private String from;

        public RoutePathBuilder to(String node) {
            this.paths.add(node);
            return this;
        }

        public RoutePath end() {
            return new RoutePath(this.paths);
        }
    }


}


