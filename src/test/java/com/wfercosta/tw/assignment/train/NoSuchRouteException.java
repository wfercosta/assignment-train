package com.wfercosta.tw.assignment.train;

public class NoSuchRouteException extends ApplicationException {

    private Route route;

    public NoSuchRouteException(Route route) {
        super(RouterExceptionCode.NO_SUCH_ROUTE, route);
    }
}
