package com.wfercosta.tw.assignment.train.router.command.filter;

import com.wfercosta.tw.assignment.train.router.model.Route;

public class RouteFilterEqualTo implements RouteFilter {
    @Override
    public boolean isSupported(RouteFilterType type) {
        return RouteFilterType.STOPS_EQUAL_TO == type;
    }

    @Override
    public boolean test(Route route, RouteFilterType type, int required) {
        return (type == RouteFilterType.STOPS_EQUAL_TO && route.stops() == required);
    }

}
