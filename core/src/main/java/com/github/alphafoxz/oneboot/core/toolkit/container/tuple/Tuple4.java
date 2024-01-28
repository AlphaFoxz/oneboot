package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

@Getter
public class Tuple4<T0, T1, T2, T3> extends Tuple3<T0, T1, T2> {
    protected final T3 index3;

    protected Tuple4(T0 index0, T1 index1, T2 index2, T3 index3) {
        super(index0, index1, index2);
        this.index3 = index3;
    }

    public static <T0, T1, T2, T3> Tuple4<T0, T1, T2, T3> of(T0 index0, T1 index1, T2 index2, T3 index3) {
        return new Tuple4<>(index0, index1, index2, index3);
    }

    public Object[] toArray() {
        return new Object[]{index0, index1, index2, index3};
    }
}
