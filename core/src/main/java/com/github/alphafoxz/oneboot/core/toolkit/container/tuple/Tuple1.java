package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import lombok.Getter;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

@Getter
public class Tuple1<T0> implements Iterable<Object>, Serializable {
    protected final T0 index0;

    protected Tuple1(T0 index0) {
        this.index0 = index0;
    }

    public static <T0> Tuple1<T0> of(T0 index0) {
        return new Tuple1<>(index0);
    }

    @Override
    public Iterator<Object> iterator() {
        return Collections.unmodifiableList(Arrays.asList(toArray())).iterator();
    }

    public Object[] toArray() {
        return new Object[]{index0};
    }

    @Override
    public String toString() {
        return Arrays.toString(toArray());
    }
}
