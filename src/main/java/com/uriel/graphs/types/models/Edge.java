package com.uriel.graphs.types.models;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@EqualsAndHashCode(of = {"u", "v"})
@AllArgsConstructor
public class Edge <T> implements Comparable<Edge<T>> {

    protected final Vertice<T> u;
    protected final Vertice<T> v;
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
                u.getId() + " -> " + v.getId() +
                (weight != null ? ", weight=" + weight : "") +
                '}';
    }

    @Override
    public int compareTo(Edge<T> o) {
        var compareToSource = this.u.compareTo(o.u);
        return (compareToSource == 0 ? this.v.compareTo(o.v) : compareToSource);
    }
}
