package ex05;

import java.util.Scanner;

public class ex02 {

	public static void main(String[] args) {

//		��� �Է¹޾� ����
		Scanner scan = new Scanner(System.in);

		while (true) {

			int num = scan.nextInt();
			System.out.println("num = " + num);

			if (num == 0) {
				break;
//		���ǿ� �������� �� break;�� �ߵ��Ǿ� while ������ �������� ����ȴ�.
			}
		}
		System.out.println("����˴ϴ�.");
	}
}
