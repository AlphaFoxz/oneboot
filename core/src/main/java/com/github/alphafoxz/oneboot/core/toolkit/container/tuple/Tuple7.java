package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

@Getter
public class Tuple7<T0, T1, T2, T3, T4, T5, T6> extends Tuple6<T0, T1, T2, T3, T4, T5> {
    protected final T6 index6;

    protected Tuple7(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4, T5 index5, T6 index6) {
        super(index0, index1, index2, index3, index4, index5);
        this.index6 = index6;
    }

    public static <T0, T1, T2, T3, T4, T5, T6> Tuple7<T0, T1, T2, T3, T4, T5, T6> of(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4, T5 index5, T6 index6) {
        return new Tuple7<>(index0, index1, index2, index3, index4, index5, index6);
    }

    public Object[] toArray() {
        return new Object[]{index0, index1, index2, index3, index4, index5, index6};
    }
}
