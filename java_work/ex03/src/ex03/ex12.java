package ex03;

public class ex12 {

	public static void main(String[] args) {
		
		int a = 10;
		int b = 10;
		
		boolean result;
		result = ((a+=10)<0) && ((b+=10)>0);
//		and �����ڸ� ����� �� 
//		���� ���� ���� ��� �� false�� ������ �޺κ��� ������� �ʴ´�.
//		�׷��� a���� 10�� �����ְ�
//		b���� ������ �ʰ� 10�� ���´�.
		System.out.println(result);
		System.out.println(a);
		System.out.println(b);
		
		
		result = ((a+=10)<0) || ((b+=10)>0);
//		or �����ڸ� ����� ��
//		���� ���� Ʋ���� �Ѱ����� ������ true�� �Ǳ� ������ �޺κб��� ������ش�.
//		�׷��� a���� ���� ��갪�� a=20�� �����Ͽ� 30�� ������
//		b���� ���� �� true�� �ش�Ǿ� 10�� ���� 20�� ���´�. 
		System.out.println(result);
		System.out.println(a);
		System.out.println(b);
	}
}
