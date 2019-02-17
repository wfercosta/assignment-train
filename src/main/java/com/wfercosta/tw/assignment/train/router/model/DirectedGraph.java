package com.wfercosta.tw.assignment.train.router.model;

import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph {

    private Map<String, Set<DirectedEdge>> graph;

    public DirectedGraph(String data) {
        this.graph = Arrays.stream(data.replaceAll(" ","").split(","))
                .map(DirectedEdge::of).collect(Collectors.groupingBy(DirectedEdge::from, Collectors.toCollection(LinkedHashSet::new)));
    }

    public static DirectedGraph from(String data) {

        return new DirectedGraph(data);
    }

    public Set<WeightedNode> adjacentsFrom(String node) {
        return this.graph.get(node).stream().map(edge -> WeightedNode.of(edge.to(), edge.distance())).collect(Collectors.toSet());
    }
}
