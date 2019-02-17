package com.wfercosta.tw.assignment.train;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;


public class Router {

    private static final Logger LOG = LoggerFactory.getLogger(Router.class);

    private DirectedGraph graph;

    public Router(String data) {

        if (StringUtils.isEmpty(data)) {
            throw new GraphInputDoesNotMatchPatternException(data);
        }

        this.graph = DirectedGraph.from(data);

    }

    public static Router withGraph(String graphData) {
        return new Router(graphData);
    }

    public Trip compute(RoutePath routePath) {

        LOG.debug(routePath.toString());

        List<Route> routes = obtainRoutesFor(routePath);

        LOG.debug(routes.toString());

        Route selected  = routes.stream().filter(route -> route.match(routePath)).findFirst().orElse(null);

        if (routePath.paths().size() > 2 && Objects.isNull(selected)) {
            throw new NoSuchRouteException(routePath);
        }

        return Trip.of(routes, selected);
    }

    private List<Route> obtainRoutesFor(RoutePath path) {
        List<WeightedNode> visited = new ArrayList<>();
        List<List<WeightedNode>> routes = new ArrayList<>();
        navigate(path.start(), path.end(), visited, routes);
        return routes.stream().map(Route::of).sorted(Comparator.comparingInt(Route::distance)).collect(Collectors.toList());
    }

    private void navigate(String current, String end, List<WeightedNode> visited, List<List<WeightedNode>> routes) {

        Set<WeightedNode> nodes = this.graph.adjacentsFrom(current);

        if (visited.isEmpty()) {
            visited.add(WeightedNode.of(current));
        }

        for (WeightedNode node : nodes) {
            if (visited.contains(node)) {
                continue;
            }

            if (node.name().equals(end)) {
                visited.add(node);
                routes.add(new ArrayList(visited));
                visited.removeIf( el -> el.equals(node));
                break;
            }
        }

        for (WeightedNode node: nodes) {
            if (visited.contains(node) || node.equals(end)) {
                continue;
            }

            visited.add(node);
            navigate(node.name(), end, visited, routes);
            visited.removeIf( el -> el.equals(node));
        }

    }

    public Trip compute(RoutePath end, int noLongerThan) {
        return null;
    }
}
