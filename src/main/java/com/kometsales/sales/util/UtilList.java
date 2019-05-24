package com.kometsales.sales.util;

import java.util.ArrayList;
import java.util.List;

public class UtilList {

    //Function that cuts a list in sublists without length view L
    public static <Sales> List<List<Sales>> chopped(List<Sales> list, final int L) {
        List<List<Sales>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L) {
            parts.add(new ArrayList<>(
                    list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }
}
