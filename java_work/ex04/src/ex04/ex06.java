package ex04;

import java.util.Scanner;

public class ex06 {

/*	���ڸ� �Է¹޾�
 * 	10 �̸��̸� 10 �̸� ���
 * 	10 �̻��̷� 20 �����̸� 10 �̻� 20 ���� ���
 * 	20 �ʰ��̸� 20 �ʰ� ��� 
 */
	
	public static void main(String[] args) {
	
		Scanner scan = new Scanner(System.in);
		
		System.out.println("���� �Է�");
		int num1 = scan.nextInt();
		
		System.out.println(num1);
		
		if(num1<10) {
			System.out.println("10 �̸� �Դϴ�.");
		}else if(10 <= num1 && num1 <= 20) {
			System.out.println("10 �̻��̰� 20 ���� �Դϴ�.");
		} else {
			System.out.println("20 �ʰ�");
		}
	}
}
