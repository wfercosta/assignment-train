package com.wfercosta.tw.assignment.train.router.command.filter;

import com.wfercosta.tw.assignment.train.router.model.Route;

public interface RouteFilter {

    boolean isSupported(RouteFilterType type);

    boolean test(Route route, RouteFilterType type, int required);

}
