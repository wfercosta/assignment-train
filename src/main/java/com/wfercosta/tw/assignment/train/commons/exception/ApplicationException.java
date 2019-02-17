package com.wfercosta.tw.assignment.train.commons.exception;

public class ApplicationException extends RuntimeException {

    public ApplicationException(ExceptionCode code, Object... values) {
        super(MessageResolver.of(code, values).resolve());
    }

}
