package ex06;

import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int year = sc.nextInt();
//	        nextInt�� Int�� �Է¹޴´�.
		String age_type = sc.next();
//	        next�� String�� �Է¹޴´�.
		int answer = 0;

//	        a == 10 <=���� ��
//	        a.equals("10") <=���ڿ� ��
		if (age_type.equals("KOREA")) {
			answer = 2030 - year +1;
			
		} else if (age_type.equals("Year")) {
			answer = 2030 - year ;
		}

		System.out.println("age+type = " + age_type);
		System.out.println("answer = " + answer);
		System.out.println("year = " + year);
	}
}
