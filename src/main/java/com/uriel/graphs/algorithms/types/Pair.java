package com.uriel.graphs.algorithms.types;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Pair<T> {
    private int index;
    private T value;
}
