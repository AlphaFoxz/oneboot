package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

@Getter
public class Tuple3<T0, T1, T2> extends Tuple2<T0, T1> {
    protected final T2 index2;

    protected Tuple3(T0 index0, T1 index1, T2 index2) {
        super(index0, index1);
        this.index2 = index2;
    }

    public static <T0, T1, T2> Tuple3<T0, T1, T2> of(T0 index0, T1 index1, T2 index2) {
        return new Tuple3<>(index0, index1, index2);
    }

    public Object[] toArray() {
        return new Object[]{index0, index1, index2};
    }
}
