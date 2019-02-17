package com.wfercosta.tw.assignment.train;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class DirectedEdge {

    private String from;
    private String to;
    private Integer distance;

    public DirectedEdge(String from, String to, int distance) {
        this.from = from;
        this.to = to;
        this.distance = distance;
    }

    public DirectedEdge(String from, String to) {
        this.from = from;
        this.to = to;
        this.distance = -1;
    }

    public static DirectedEdge of(String from, String to) {
        return new DirectedEdge(from, to);
    }

    public static DirectedEdge of(String edge) {

        if (StringUtils.isEmpty(edge)) {
            throw new GraphInputDoesNotMatchPatternException(edge);
        }

        return new DirectedEdge(edge.substring(0, 1), edge.substring(1, 2), Integer.parseInt(edge.substring(2)));
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
