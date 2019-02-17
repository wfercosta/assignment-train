package com.wfercosta.tw.assignment.train.router.model;

import com.wfercosta.tw.assignment.train.router.exceptions.GraphInputDoesNotMatchPatternException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DirectedEdge {

    public static final String PATTERN_EXPRESSION = "[A-Z]{2}[0-9]+";
    private String from;
    private String to;
    private Integer distance;

    public DirectedEdge(String edge) {
        initialize(edge);
    }

    private void initialize(String edge) {
        validateEdge(edge);
        this.from = edge.substring(0, 1);
        this.to = edge.substring(1, 2);
        this.distance = Integer.parseInt(edge.substring(2));
    }

    public static DirectedEdge of(String edge) {
        return new DirectedEdge(edge);
    }

    private void validateEdge(String edge) {

        if (StringUtils.isEmpty(edge)) {
            throw new GraphInputDoesNotMatchPatternException(edge);
        }

        Pattern pattern = Pattern.compile(PATTERN_EXPRESSION);
        Matcher matcher = pattern.matcher(edge);
        if (!matcher.find()) {
            throw new GraphInputDoesNotMatchPatternException(edge);
        }

    }


    public String from() {
        return this.from;
    }

    public String to() {
        return this.to;
    }

    public Integer distance() {
        return this.distance;
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "distance");
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "distance");
    }

    @Override
    public String toString() {
        return "DirectedEdge: [from: " + from + ", to:" + to  +", distance: " + distance +"]";
    }
}
