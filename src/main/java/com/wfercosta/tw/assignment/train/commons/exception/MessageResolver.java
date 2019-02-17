package com.wfercosta.tw.assignment.train.commons.exception;

import java.text.MessageFormat;
import java.util.ResourceBundle;

public class MessageResolver {

    private static ResourceBundle bundle;

    static {
        bundle = ResourceBundle.getBundle("messages");
    }

    private final ExceptionCode code;
    private final Object[] values;

    public MessageResolver(ExceptionCode code, Object[] values) {
        this.code = code;
        this.values = values;
    }

    public static MessageResolver of(ExceptionCode code, Object[] values) {
        return new MessageResolver(code, values);
    }


    public String resolve() {

        if (bundle.containsKey(code.getKey())) {
            return MessageFormat.format(bundle.getString(code.getKey()), values);
        }

        return MessageFormat.format(bundle.getString(ExceptionCode.DEFAULT_KEY), code.getKey());
    }
}
