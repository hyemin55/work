package ex03;

import java.util.Scanner;

public class ex03 {
	public static void main(String[] args) {

//	�� ���� �Է� �޾� ����, �������� �Ͽ� ��� �������� ���Ͻÿ�.
//	�ֿܼ��� �Է¹ޱ�
		Scanner scan = new Scanner(System.in);
		
		int a;
		int b;
		
		System.out.println("���� �Է� :");
		a = scan.nextInt(); //��������� �Է� �޴� ����
		b = scan.nextInt();
		
		System.out.println(a +"*" +b +"=" +a*b);
		System.out.println(a +"/" +b +"=" +a/b);
		System.out.println(a%b);
//		%�� ������ ������.
		
	}
}
