package ex06;

public class ex04 {

//	���´� ������ ȣ�� �ϴ� �ż��� => 2, 3
//	(int dan)�� main �Լ� ���� (5)�� �̸��� ������ ��. 
	public static void aa(int dan) {
		for (int i = 1; i < 10; i++)
			System.out.println("aa = " + dan * i);
	}

//	�� ���� �޾Ƽ� �ּ� ����� ����ϴ� �ż���
//	num1�� �������� �� �������� 0�̰� �׸��� num2�� �������� �� �������� 0
	public static void bb(int num1, int num2) {
		System.out.println("bb");
		int num = 0;
		while (num < 50) {
			num++;
			if (num % num1 == 0 && num % num2 == 0) {
				System.out.println("�ּҰ������ = " + num);
				break;
			}
		}
	}

	
	public static void main(String[] args) {
		System.out.println("���α׷� ����");
		aa(5);
		bb(3, 4);
		System.out.println("���α׷� ��");
	}
}
