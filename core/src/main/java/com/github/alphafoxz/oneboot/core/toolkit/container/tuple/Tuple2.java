package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

@Getter
public class Tuple2<T0, T1> extends Tuple1<T0> {
    protected final T1 index1;

    protected Tuple2(T0 index0, T1 index1) {
        super(index0);
        this.index1 = index1;
    }

    public static <T0, T1> Tuple2<T0, T1> of(T0 index0, T1 index1) {
        return new Tuple2<>(index0, index1);
    }

    public Object[] toArray() {
        return new Object[]{index0, index1};
    }
}
