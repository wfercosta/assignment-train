package com.wfercosta.tw.assignment.train;

import com.wfercosta.tw.assignment.train.commons.exception.ApplicationException;
import com.wfercosta.tw.assignment.train.router.model.Route;
import com.wfercosta.tw.assignment.train.router.model.RoutePath;
import com.wfercosta.tw.assignment.train.router.model.Trip;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Main {

    public static final Logger LOG = LoggerFactory.getLogger(Main.class);
    public static final String BLOCK_SPACE = "    ";

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.println("Inform the graph edges (e.g. AB9, AD5, BD1): ");
                Router router = Router.withGraph(scanner.nextLine());


                System.out.println("Inform the route path (e.g. A, B, C):");
                Trip trip = router.compute(RoutePath.withPath(scanner.nextLine()));

                printSelectedRoute(trip);
                printShortestRoute(trip);
                printPossibleRoutes(trip);
            } catch (ApplicationException e) {
                LOG.error(e.getMessage());
            }
        }

    }

    private static void printSelectedRoute(Trip trip) {
        System.out.println();
        System.out.println("Possible selected: ");
        Route route = trip.selected();
        printRouteInfo(route);
    }

    private static void printRouteInfo(Route route) {
        System.out.println(BLOCK_SPACE.concat("Path: ").concat(route.path().toString()));
        System.out.println(BLOCK_SPACE.concat("Distance: ").concat(Integer.toString(route.distance())));
        System.out.println(BLOCK_SPACE.concat("Stops: ").concat(Integer.toString(route.stops())));
    }

    private static void printShortestRoute(Trip trip) {
        System.out.println();
        System.out.println("Possible shortest: ");
        Route route = trip.shortestRoute();
        printRouteInfo(route);
    }

    private static void printPossibleRoutes(Trip trip) {
        System.out.println();
        System.out.println("Possible routes: ");
        for (Route route: trip.routes()) {
            printRouteInfo(route);
            System.out.println(BLOCK_SPACE.concat("--"));
            System.out.println();
        }
    }
}
