/*
2. 숫자로 이루어진 문자열이 있을 때 각 자리의 합을 더한 결과를 출력하는 코드를 완성하라
        만일 문자열이 "12345" 면 ‘1+2+3+4+5’ 결과인 15를 출력하여야한다.
        (1) 에 알맞은 코드를 넣으시오 .
        [Hint] String 클래스의 charAt(int i) 을 사용
 */

public class exam2 {
    public static void main(String[] args) {
        String str = "12345";
        int sum = 0;

        for (int i = 0; i < str.length(); i++) {
            char a = str.charAt(i);
            sum = a + sum - 48;
            System.out.println("a = " + a);
        }
        System.out.println("sum = " + sum);
    }
}

