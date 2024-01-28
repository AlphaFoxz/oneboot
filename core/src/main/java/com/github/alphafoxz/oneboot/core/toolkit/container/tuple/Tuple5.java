package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

@Getter
public class Tuple5<T0, T1, T2, T3, T4> extends Tuple4<T0, T1, T2, T3> {
    protected final T4 index4;

    protected Tuple5(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4) {
        super(index0, index1, index2, index3);
        this.index4 = index4;
    }

    public static <T0, T1, T2, T3, T4> Tuple5<T0, T1, T2, T3, T4> of(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4) {
        return new Tuple5<>(index0, index1, index2, index3, index4);
    }

    public Object[] toArray() {
        return new Object[]{index0, index1, index2, index3, index4};
    }
}
