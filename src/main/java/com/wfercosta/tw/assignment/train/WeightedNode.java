package com.wfercosta.tw.assignment.train;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class WeightedNode {

    private String name;
    private int weight;

    public WeightedNode(String name) {
        this.name = name;
    }

    public WeightedNode(String name, Integer weight) {
        this(name);
        this.weight = weight;
    }

    public static WeightedNode of(String name) {
        return new WeightedNode(name);
    }

    public static WeightedNode of(String name, Integer weight) {
        return new WeightedNode(name, weight);
    }

    public String name() {
        return this.name;
    }

    public int weight() {
        return this.weight;
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, "weight");
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, "weight");
    }

    @Override
    public String toString() {
        return "WeightedNode [ name=" + name + ", weight=" + weight + "]";
    }
}
