package com.wfercosta.tw.assignment.train.router.exceptions;

import com.wfercosta.tw.assignment.train.commons.exception.ApplicationException;

public class GraphInputDoesNotMatchPatternException extends ApplicationException {
    public GraphInputDoesNotMatchPatternException(String graphData) {
        super(RouterExceptionCode.INVALID_INPUT_DATA, graphData);
    }
}
