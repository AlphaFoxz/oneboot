package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

@Getter
public class Tuple6<T0, T1, T2, T3, T4, T5> extends Tuple5<T0, T1, T2, T3, T4> {
    protected final T5 index5;

    protected Tuple6(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4, T5 index5) {
        super(index0, index1, index2, index3, index4);
        this.index5 = index5;
    }

    public static <T0, T1, T2, T3, T4, T5> Tuple6<T0, T1, T2, T3, T4, T5> of(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4, T5 index5) {
        return new Tuple6<>(index0, index1, index2, index3, index4, index5);
    }

    public Object[] toArray() {
        return new Object[]{index0, index1, index2, index3, index4, index5};
    }
}
