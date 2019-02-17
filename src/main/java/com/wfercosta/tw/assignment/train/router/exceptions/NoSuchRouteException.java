package com.wfercosta.tw.assignment.train.router.exceptions;

import com.wfercosta.tw.assignment.train.router.model.RoutePath;
import com.wfercosta.tw.assignment.train.commons.exception.ApplicationException;

public class NoSuchRouteException extends ApplicationException {

    private RoutePath routePath;

    public NoSuchRouteException(RoutePath routePath) {
        super(RouterExceptionCode.NO_SUCH_ROUTE, routePath);
    }
}
