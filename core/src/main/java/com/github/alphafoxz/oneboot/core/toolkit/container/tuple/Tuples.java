package com.github.alphafoxz.oneboot.core.toolkit.container.tuple;

import com.github.alphafoxz.oneboot.core.exceptions.OnebootApiDesignException;
import org.springframework.http.HttpStatus;

public abstract class Tuples {
    @Deprecated
    public static Tuple1 fromArray(Object[] list) {
        //noinspection ConstantConditions
        if (list == null || list.length > 7) {
            throw new OnebootApiDesignException("元组中的元素数量不正确，不推荐使用Tuples.fromArray方法，请检查源数组数量，推荐使用带有类型检查的of进行元组初始化", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        switch (list.length) {
            case 1 -> {
                return of(list[0]);
            }
            case 2 -> {
                return of(list[0], list[1]);
            }
            case 3 -> {
                return of(list[0], list[1], list[2]);
            }
            case 4 -> {
                return of(list[0], list[1], list[2], list[3]);
            }
            case 5 -> {
                return of(list[0], list[1], list[2], list[3], list[4]);
            }
            case 6 -> {
                return of(list[0], list[1], list[2], list[3], list[4], list[5]);
            }
            case 7 -> {
                return of(list[0], list[1], list[2], list[3], list[4], list[5], list[6]);
            }
        }
        throw new IllegalArgumentException("too many arguments (" + list.length + "), need between 2 and 8 values");
    }

    public static <T0> Tuple1<T0> of(T0 index0) {
        return Tuple1.of(index0);
    }

    public static <T0, T1> Tuple2<T0, T1> of(T0 index0, T1 index1) {
        return Tuple2.of(index0, index1);
    }

    public static <T0, T1, T2> Tuple3<T0, T1, T2> of(T0 index0, T1 index1, T2 index2) {
        return Tuple3.of(index0, index1, index2);
    }

    public static <T0, T1, T2, T3> Tuple4<T0, T1, T2, T3> of(T0 index0, T1 index1, T2 index2, T3 index3) {
        return Tuple4.of(index0, index1, index2, index3);
    }

    public static <T0, T1, T2, T3, T4> Tuple5<T0, T1, T2, T3, T4> of(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4) {
        return Tuple5.of(index0, index1, index2, index3, index4);
    }

    public static <T0, T1, T2, T3, T4, T5> Tuple6<T0, T1, T2, T3, T4, T5> of(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4, T5 index5) {
        return Tuple6.of(index0, index1, index2, index3, index4, index5);
    }

    public static <T0, T1, T2, T3, T4, T5, T6> Tuple7<T0, T1, T2, T3, T4, T5, T6> of(T0 index0, T1 index1, T2 index2, T3 index3, T4 index4, T5 index5, T6 index6) {
        return Tuple7.of(index0, index1, index2, index3, index4, index5, index6);
    }
}
