package com.wfercosta.tw.assignment.train.router.exceptions;

import com.wfercosta.tw.assignment.train.commons.exception.ExceptionCode;

public enum RouterExceptionCode implements ExceptionCode {
    INVALID_INPUT_DATA, INVALID_NODE, NO_SUCH_ROUTE;

    @Override
    public String getKey() {
        return this.name();
    }
}
