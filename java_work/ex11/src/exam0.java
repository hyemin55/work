//3.
//        7개의 정수를 입력받아 내림차순으로 정렬하는 프로그램을 만들어라
//        사용자가
//6,9,15,3,2,11,20 입력했다면
//20,15,11,9,6,3,2 출력되어야 한다.

import java.util.Scanner;

public class exam0 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int a[] = new int[7];


        for (int i = 0; i > 7; i++) {
            a[i] = scanner.nextInt();
        }
    }
}