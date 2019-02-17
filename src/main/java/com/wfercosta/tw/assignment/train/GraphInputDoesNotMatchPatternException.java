package com.wfercosta.tw.assignment.train;

public class GraphInputDoesNotMatchPatternException extends ApplicationException {
    public GraphInputDoesNotMatchPatternException(String graphData) {
        super(RouterExceptionCode.INVALID_INPUT_DATA, graphData);
    }
}
