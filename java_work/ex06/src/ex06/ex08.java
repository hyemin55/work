package ex06;

import java.util.Scanner;

public class ex08 {

//	int�� ������ return�� �Է�.
	public static int doA(int num) {
		return num * num;
		
	}
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		while(true) {
			System.out.println("���ϰ� ���� �� �Է�");
			int input = scan.nextInt();
			System.out.println(doA(input));
		}
	}
}
