package com.wfercosta.tw.assignment.train;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class RouterTest {

    private final static String TEST_GRAPH_DATA="AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";

    private Router router;

    @BeforeAll
    public void setup() {
        router = Router.withGraph(TEST_GRAPH_DATA);
    }

    @Test
    @DisplayName("1# Should return distance 9 When the route were A-B-C")
    public void Should_ReturnDistance9_When_RouteWhereABC() {

        int distance = router.distanceOf(Route
                                            .segment()
                                                .from("A")
                                                .to("B")
                                                .to("C")
                                            .end());

        assertEquals(9, distance);
    }

    @Test
    @DisplayName("2# Should return distance 5 When the route were A-D")
    public void Should_ReturnDistance5_When_RouteWhereAD() {

        int distance = router.distanceOf(Route
                                            .segment()
                                                .from("A")
                                                .to("D")
                                            .end());

        assertEquals(5, distance);
    }

    @Test
    @DisplayName("3# Should return distance 13 When the route were A-D-C")
    public void Should_ReturnDistance13_When_RouteWhereADC() {

        int distance = router.distanceOf(Route
                                            .segment()
                                                .from("A")
                                                .to("D")
                                                .to("C")
                                            .end());

        assertEquals(13, distance);
    }

    @Test
    @DisplayName("4# Should return distance 22 When the route were A-E-B-C-D")
    public void Should_ReturnDistance22_When_RouteWhereAEBCD() {

        int distance = router.distanceOf(Route
                                            .segment()
                                                .from("A")
                                                .to("E")
                                                .to("B")
                                                .to("C")
                                                .to("D")
                                            .end());

        assertEquals(22, distance);
    }


    @Test
    @DisplayName("5# Should return distance NO SUCH ROUTE When the route were A-E-D")
    public void Should_ReturnDistanceNoSuchRoute_When_RouteWhereAED() {

        Assertions.assertThrows(NoSuchRouteException.class, () -> {
            router.distanceOf(Route
                    .segment()
                      .from("A")
                      .to("E")
                      .to("D")
                    .end());
        });

    }

    @Test
    @DisplayName("6# Should return 2 trips When the trip starts in C and ends in C")
    public void Should_Return2Trips_When_TripStartsAndEndsInC() {

    }

    @Test
    @DisplayName("7# Should return 3 trips When the trip starts in A and ends in C")
    public void Should_Return3Trips_When_TripStartsAAndEndsInC() {

    }

    @Test
    @DisplayName("8# Should return shortest route with len 9 When the route is from A to C")
    public void Should_ReturnShortestRouteLen9_When_RouteFromAToC() {

    }

    @Test
    @DisplayName("9# Should return shortest route with len 9 When the route is from B to B")
    public void Should_ReturnShortestRouteLen9_When_RouteFromBToB() {

    }


    @Test
    @DisplayName("10# Should return 7 routes with distance less than 30 When the route is from C to C")
    public void Should_Return7RoutesWithDistanceLessThan30_When_RouteFromCToC() {

    }


}
