package com.wfercosta.tw.assignment.train;

import static com.wfercosta.tw.assignment.train.RouteFilterType.DISTANCES_LESS_THAN_EQUAL_TO;
import static com.wfercosta.tw.assignment.train.RouteFilterType.STOPS_LESS_THAN_EQUAL_TO;

public class RouteFilterLessThanEqualTo implements RouteFilter {

    @Override
    public boolean isSupported(RouteFilterType type) {
        return DISTANCES_LESS_THAN_EQUAL_TO == type || STOPS_LESS_THAN_EQUAL_TO == type;
    }

    @Override
    public boolean test(Route route, RouteFilterType type, int required) {

        switch (type) {
            case DISTANCES_LESS_THAN_EQUAL_TO:
                return route.distance() <= required;
            case STOPS_LESS_THAN_EQUAL_TO:
                return route.stops() <= required;
            default:
                return true;
        }

    }

}
