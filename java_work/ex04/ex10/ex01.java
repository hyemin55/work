import java.util.Scanner;

/*2. 피보나치 수열 은 앞을 두 수를 더해서 다음 수를 만들어 나가는 수열이다

예를 들어 앞의 두 수가 1과 1이라면 그 다음 수는 2가 되고 그 다음 수는 1과 2를 더해서 3이 된다.
        1,1,2,3,5,8,13,21,... 이렇게 진행된다
피보나치수열의 10 번째 수는 무엇인지 계산하는 프로그램을 완성하시오
*/
public class ex01 {
    public static void main(String[] args) {

        System.out.println("숫자 2개를 입력하세요");
        Scanner scan = new Scanner(System.in);
        int num1 = scan.nextInt();
        Scanner scan2 = new Scanner(System.in);
        int num2 = scan2.nextInt();

        int a = num1;
        int b = num2;
        int i=1;
        int c=0;

        for(; i<10 ; i++){
            b = a+b;
            c = b+c-a;

            System.out.println(c);
        }
        System.out.println(c);
    }
}
