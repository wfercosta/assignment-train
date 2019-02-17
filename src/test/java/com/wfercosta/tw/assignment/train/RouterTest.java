package com.wfercosta.tw.assignment.train;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class RouterTest {

    private final static String TEST_GRAPH_DATA="AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    private Router router;

    @BeforeEach
    public void setup() {
        router = Router.withGraph(TEST_GRAPH_DATA);
    }

    @Test
    @DisplayName("1# Should return distance 9 When the route were A-B-C")
    public void Should_ReturnDistance9_When_RouteWhereABC() {

        Trip trip = router.compute(
                RoutePath
                    .startAt("A")
                        .to("B")
                        .to("C")
                    .end()
        );

        assertEquals(9, trip.selected().distance());
    }

    @Test
    @DisplayName("2# Should return distance 5 When the route were A-D")
    public void Should_ReturnDistance5_When_RouteWhereAD() {

        Trip trip = router.compute(
                RoutePath
                    .startAt("A")
                        .to("D")
                    .end()
        );

        assertEquals(5, trip.selected().distance());
    }

    @Test
    @DisplayName("3# Should return distance 13 When the route were A-D-C")
    public void Should_ReturnDistance13_When_RouteWhereADC() {

        Trip trip = router.compute(
                RoutePath
                    .startAt("A")
                        .to("D")
                        .to("C")
                    .end()
        );

        assertEquals(13, trip.selected().distance());
    }

    @Test
    @DisplayName("4# Should return distance 22 When the route were A-E-B-C-D")
    public void Should_ReturnDistance22_When_RouteWhereAEBCD() {

        Trip trip = router.compute(
                RoutePath
                    .startAt("A")
                        .to("E")
                        .to("B")
                        .to("C")
                        .to("D")
                    .end()
        );

        assertEquals(22, trip.selected().distance());
    }


    @Test
    @DisplayName("5# Should return distance NO SUCH ROUTE When the route were A-E-D")
    public void Should_ReturnDistanceNoSuchRoute_When_RouteWhereAED() {

        Assertions.assertThrows(NoSuchRouteException.class, () -> router.compute(
                RoutePath
                    .startAt("A")
                        .to("E")
                        .to("D")
                    .end()
                ));

    }

    @Test
    @DisplayName("6# Should return 2 trips When the trip starts in C and ends in C")
    public void Should_Return2Trips_When_TripStartsAndEndsInC() {

        Trip trip = router.compute(
                RoutePath
                        .startAt("C")
                        .to("C")
                        .filter(RouteFilterType.STOPS_LESS_THAN_EQUAL_TO, 3)
                        .end()
        );

        assertEquals(2, trip.routes().size());
        assertTrue(trip.routes().stream().anyMatch( route -> route.stops() == 2));
        assertTrue(trip.routes().stream().anyMatch( route -> route.stops() == 3));

    }

    @Test
    @DisplayName("7# Should return 3 trips When the trip starts in A and ends in C")
    public void Should_Return3Trips_When_TripStartsAAndEndsInC() {

        Trip trip = router.compute(
                RoutePath
                        .startAt("A")
                            .to("C")
                        .filter(RouteFilterType.STOPS_EQUAL_TO, 4)
                        .end()
        );

        assertEquals(3, trip.routes().size());
        assertTrue(trip.routes().stream().allMatch( route -> route.stops() == 4));

    }

    @Test
    @DisplayName("8# Should return the shortest route distance with len 9 When the route is from A to C")
    public void Should_ReturnShortestRouteDistanceLen9_When_RouteFromAToC() {

        Trip trip = router.compute(
                RoutePath
                        .startAt("A")
                            .to("C")
                        .end()
        );

        assertEquals(9, trip.shortestRoute().distance());

    }

    @Test
    @DisplayName("9# Should return the shortest route distance with len 9 When the route is from B to B")
    public void Should_ReturnShortestRouteDistanceLen9_When_RouteFromBToB() {

        Trip trip = router.compute(
                RoutePath
                        .startAt("B")
                            .to("B")
                        .end()
        );

        assertEquals(9, trip.shortestRoute().distance());

    }


    @Test
    @DisplayName("10# Should return 7 routes with distance len less than 30 When the route is from C to C")
    public void Should_Return7RoutesWithDistanceLenLessThan30_When_RouteFromCToC() {

        Trip trip = router.compute(
                RoutePath
                        .startAt("C")
                            .to("C")
                        .filter(RouteFilterType.DISTANCES_LESS_THAN_EQUAL_TO, 30)
                        .end()
        );

        assertEquals(7, trip.routes().size());
        assertTrue(trip.routes().stream().allMatch( route -> route.distance() < 30));

    }


}
