package com.wfercosta.tw.assignment.train.router.model;

import java.util.*;
import java.util.stream.Collectors;

public class DirectedGraph {

    private Map<String, Set<DirectedEdge>> graph;

    public DirectedGraph(List<String> data) {
        this.graph = data.stream().map(DirectedEdge::of).collect(Collectors.groupingBy(DirectedEdge::from, Collectors.toCollection(LinkedHashSet::new)));
    }

    public static DirectedGraph from(List<String> edges) {
        return new DirectedGraph(edges);
    }

    public Set<WeightedNode> adjacentsFrom(String node) {
        return this.graph.get(node).stream().map(edge -> WeightedNode.of(edge.to(), edge.distance())).collect(Collectors.toSet());
    }
}
