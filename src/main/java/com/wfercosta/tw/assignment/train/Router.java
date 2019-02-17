package com.wfercosta.tw.assignment.train;

import com.wfercosta.tw.assignment.train.router.command.filter.RouteFilter;
import com.wfercosta.tw.assignment.train.router.command.filter.RouteFilterEqualTo;
import com.wfercosta.tw.assignment.train.router.command.filter.RouteFilterLessThanEqualTo;
import com.wfercosta.tw.assignment.train.router.command.filter.RouteFilterType;
import com.wfercosta.tw.assignment.train.router.exceptions.GraphInputDoesNotMatchPatternException;
import com.wfercosta.tw.assignment.train.router.exceptions.NoSuchRouteException;
import com.wfercosta.tw.assignment.train.router.model.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class Router {

    private static final Logger LOG = LoggerFactory.getLogger(Router.class);
    public static final String REGULAR_EXPRESSION = "([A-Z]{2}[1-9]),\\s|([A-Z]{2}[1-9])";

    private DirectedGraph graph;
    private List<RouteFilter> filterTypes = new ArrayList<>();

    public Router(String data) {
        validateAndInitializeInputData(data);
        initializeFilterTypes();
    }

    private void validateAndInitializeInputData(String data) {
        List<String> edges = validateAndExtractEdges(data);
        this.graph = DirectedGraph.from(edges);
    }

    private List<String> validateAndExtractEdges(String data) {
        Pattern pattern = Pattern.compile(REGULAR_EXPRESSION);

        Matcher matcher = pattern.matcher(data);

        if (!matcher.find()) {
            throw new GraphInputDoesNotMatchPatternException(data);
        }

        List<String> edges = new ArrayList<>();

        do {
            edges.add(Optional.ofNullable(matcher.group(2)).orElse(matcher.group(1)));
        } while(matcher.find());

        return edges;
    }

    private void initializeFilterTypes() {
        filterTypes.add(new RouteFilterEqualTo());
        filterTypes.add(new RouteFilterLessThanEqualTo());
    }

    public static Router withGraph(String graphData) {
        return new Router(graphData);
    }

    public Trip compute(RoutePath routePath) {

        LOG.debug(routePath.toString());

        List<Route> routes = obtainRoutesFor(routePath);

        LOG.debug(routes.toString());

        Route selected = validateAndObtainUserSelectedRoute(routePath, routes);

        routes = applyRouteFilters(routePath, routes);

        LOG.debug(routes.toString());

        return Trip.of(routes, selected);
    }

    private Route validateAndObtainUserSelectedRoute(RoutePath routePath, List<Route> routes) {
        Route selected  = routes.stream().filter(route -> route.match(routePath)).findFirst().orElse(null);

        if (routePath.paths().size() > 2 && Objects.isNull(selected)) {
            throw new NoSuchRouteException(routePath);
        }
        return selected;
    }

    private List<Route> applyRouteFilters(RoutePath routePath, List<Route> routes) {
        if(!routePath.filters().isEmpty()) {

            for (Map.Entry<RouteFilterType, Integer> entry : routePath.filters().entrySet()) {

                Optional<RouteFilter> filter = filterTypes.stream().filter(type -> type.isSupported(entry.getKey())).findFirst();

                if (filter.isPresent()) {
                    routes = routes.stream().filter(route -> filter.get().test(route, entry.getKey(), entry.getValue())).collect(Collectors.toList());
                }
            }

        }
        return routes;
    }

    private List<Route> obtainRoutesFor(RoutePath path) {
        List<WeightedNode> visited = new ArrayList<>();
        List<List<WeightedNode>> routes = new ArrayList<>();
        navigate(path.start(), path.end(), visited, routes);
        return routes.stream().map(Route::of).sorted(Comparator.comparingInt(Route::distance)).collect(Collectors.toList());
    }

    private void navigate(String current, String end, List<WeightedNode> visited, List<List<WeightedNode>> routes) {

        Set<WeightedNode> nodes = this.graph.adjacentsFrom(current);

        LOG.debug("Weighted Nodes for {}: {}", current, nodes);

        if (visited.isEmpty()) {
            visited.add(WeightedNode.of(current));
        }

        for (WeightedNode node : nodes) {

            if (visited.contains(node)) {
                LOG.debug("L1 - Already contains node {}: ", node);
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
            if (visited.contains(node) || (node.equals(end))) {
                LOG.debug("L2 - Already contains node {}: ", node);
                continue;
            }

            visited.add(node);
            navigate(node.name(), end, visited, routes);
            visited.removeIf( el -> el.equals(node));
        }

    }

}
