package com.wfercosta.tw.assignment.train;

public class NoSuchRouteException extends ApplicationException {

    private RoutePath routePath;

    public NoSuchRouteException(RoutePath routePath) {
        super(RouterExceptionCode.NO_SUCH_ROUTE, routePath);
    }
}
