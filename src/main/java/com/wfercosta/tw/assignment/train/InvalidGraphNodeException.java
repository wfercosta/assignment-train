package com.wfercosta.tw.assignment.train;

public class InvalidGraphNodeException extends ApplicationException {
    public InvalidGraphNodeException(RoutePath routePath) {
        super(RouterExceptionCode.INVALID_NODE, routePath);
    }
}
