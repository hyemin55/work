package org.example;

import java.util.Optional;

public class ex04 {
    public static void main(String[] args) {

        Optional<String> os1 = Optional.of("TOY1");
        System.out.println(os1);
        System.out.println(os1.get());

        Optional<String> os2 = Optional.ofNullable("TOY2");
        System.out.println(os2);
        System.out.println(os2.get());

        Optional<String> os3 = Optional.ofNullable(null);
        System.out.println(os3);
//        System.out.println(os3.get());
//        ifPresent는 os3안에 값이 들어가 있으면 출력하고 없으면 출력하지 않는다.
        os3.ifPresent(System.out::println);
        os3.ifPresentOrElse(System.out::println,()-> System.out.println("없음"));
    }
}
