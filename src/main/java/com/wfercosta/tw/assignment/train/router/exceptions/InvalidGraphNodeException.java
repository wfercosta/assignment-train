package com.wfercosta.tw.assignment.train.router.exceptions;

import com.wfercosta.tw.assignment.train.router.model.RoutePath;
import com.wfercosta.tw.assignment.train.commons.exception.ApplicationException;

public class InvalidGraphNodeException extends ApplicationException {
    public InvalidGraphNodeException(RoutePath routePath) {
        super(RouterExceptionCode.INVALID_NODE, routePath);
    }
}
