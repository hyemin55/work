package ex06;

import java.util.Scanner;

public class ex09 {

	public static void doA() {
		System.out.println("doA �Լ�");
	}

	public static void doB() {
		System.out.println("doB �Լ�");
	}

	public static void main(String[] args) {

//		��� 
//		1���� �Է� daA() �Լ� ȣ��
//		2���� �Է��ϸ� doB() �Լ� ȣ��

		Scanner scan = new Scanner(System.in);
		int select = scan.nextInt();

		if (select == 1)
			doA();
		else if (select != 2)
			doB();
	}
}
