package ex03;

import java.util.Scanner;

public class ex02 {

	public static void main(String[] args) {
		
//		���ڸ� �Է¹޾� �� ���� ���� ���Ͻÿ�.
//		���� �ϸ� ���Ⱚ ����.
		
		int a;
		int b;
//		�����ΰ��� �Է� �޾Ƽ� ����� �� ���� ���� ctrl+space �ڵ��ϼ�!
		Scanner scan = new Scanner(System.in);
		System.out.println("�����Է�");
		
//		a������ ���� �Է� �޾� �ִ� ����
		a = scan.nextInt();
		System.out.println("a =  "+a);

//		��������� a��, b���� ���������� ���ڸ� �Է��ϸ� �ȴ�.
		b = scan.nextInt();
		System.out.println("b = "+b);
		
		a = scan.nextInt();
		System.out.println("a =  "+a);

//		��������� a��, b���� ���������� ���ڸ� �Է��ϸ� �ȴ�.
		b = scan.nextInt();
		System.out.println("b = "+b);
	}
}
