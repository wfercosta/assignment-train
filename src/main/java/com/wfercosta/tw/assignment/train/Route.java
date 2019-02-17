package com.wfercosta.tw.assignment.train;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Route {

    private int stops;
    private int distance;
    private List<String> path;

    public Route(List<WeightedNode> weightedNodes) {
        this.stops = weightedNodes.size() - 1;
        this.distance = weightedNodes.stream().mapToInt(WeightedNode::weight).sum();
        this.path = weightedNodes.stream().map(WeightedNode::name).collect(Collectors.toList());
    }

    public int distance() {
        return this.distance;
    }

    public int stops() {
        return this.stops;
    }

    public List<String> path() {
        return Collections.unmodifiableList(this.path);
    }

    public static Route of(List<WeightedNode> weightedNodes) {
        return new Route(weightedNodes);
    }

    @Override
    public String toString() {
        return "Route [distance=" + distance + ", stops=" + stops + ", path=" + path + "]";
    }
}
