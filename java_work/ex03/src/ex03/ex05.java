package ex03;

public class ex05 {

	public static void main(String[] args) {
		
//		ctrl + 1 ���� ����ó��
		double a = 10.345;
		int b = (int) a;
//		a��� ������ b�� �ְ� ���� �� 
//		int���� double�� �� ũ�⶧���� �ڷ�ս��� �Ͼ ����ȯ�� �ʿ��ϴ�.
//		int = 4byte	double = 8byte
		
		System.out.println(a);
		System.out.println(b);

		
		a = 11.39;
//		double a = 11.39;�� �νĵȴ�.
//		�� ���Ͼȿ��� �ΰ��� �������� �Է��� �� ����.
//		ex) double a=10.345;
//			double a=11.39;
//			����� ����X.
		
		b = (int) a;
		System.out.println(a);
		System.out.println(b);
		
		
	}
}
