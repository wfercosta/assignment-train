package com.wfercosta.tw.assignment.train;

public interface RouteFilter {

    boolean isSupported(RouteFilterType type);

    boolean test(Route route, RouteFilterType type, int required);

}
