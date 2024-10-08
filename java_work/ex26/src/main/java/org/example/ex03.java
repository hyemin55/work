package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

interface MyPred<T> {
    boolean mytest(T t);
}

public class ex03 {

    public static int sum2(MyPred<Integer> predicate, List<Integer> list) {
        int mysum = 0;
        for (int n : list) {
            if (predicate.mytest(n))
                mysum = mysum + n;
        }
        return mysum;
    }

    public static int sum(Predicate<Integer> predicate, List<Integer> list) {
        int mysum = 0;
        for (int n : list) {
            if (predicate.test(n))
                mysum += n;
        }
        return mysum;
    }

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(10, 5, 3, 9, 1, 15, 8, 5);
        int result = sum(integer -> integer % 2 == 0, list);
        System.out.println(result);
    }
}
