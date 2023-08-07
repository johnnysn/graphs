package com.uriel.graphs.types.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"from", "to"})
@AllArgsConstructor
public class Edge <T> implements Comparable<Edge<T>> {

    protected final Vertice<T> from;
    protected final Vertice<T> to;
    @Setter
    protected Double weight;

    public static <T> Edge<T> of(Vertice<T> u, Vertice<T> v) {
        return new Edge<>(u, v, null);
    }

    public static <T> Edge<T> of(Vertice<T> u, Vertice<T> v, Double weight) {
        return new Edge<>(u, v, weight);
    }

    public static <T> Edge<T> of(int uId, int vId) {
        return new Edge<>(Vertice.of(uId), Vertice.of(vId), null);
    }

    @Override
    public String toString() {
        return "e{" +
                from.getId() + " -> " + to.getId() +
                (weight != null ? ", weight=" + weight : "") +
                '}';
    }

    @Override
    public int compareTo(Edge<T> o) {
        var compareToSource = this.from.compareTo(o.from);
        return (compareToSource == 0 ? this.to.compareTo(o.to) : compareToSource);
    }
}
