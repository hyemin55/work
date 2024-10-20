package org.example;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.stream()
                .forEach(s -> System.out.print(s+"  "));
        System.out.println();

        int result = list.stream()
                .mapToInt(value -> value)
                .sum();

        System.out.println("result = "+result);


        result = list.stream()
                .reduce(0,((integer, integer2) -> integer+integer2));

        System.out.println("result = "+result);

        result = list.stream()
                .filter(integer -> integer%2==0)
                .reduce(0,((a, b) -> a+b));

        System.out.println("짝수합은 = "+result);


        result = list.stream()
                .filter(integer -> integer%2==1)
                .reduce(0,((a, b) -> a+b));

        System.out.println("홀수합은 = "+result);
    }
}