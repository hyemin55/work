package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ex05 {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(11,22,33,44,55,66,7,8,20,40,1);
        list = new ArrayList<>(list);
        list.removeIf( integer -> integer%2 == 0);

        System.out.println(list);

        for(int i=0; i<list.size(); i++){
            int temp = list.get(i);
            System.out.println("temp = "+temp);
            if(temp%2 == 0)
                list.remove(i);
        }
        System.out.println(list);
    }
}
