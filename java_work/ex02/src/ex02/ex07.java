package ex02;

public class ex07 {

	public static void main(String[] args) {
		final int MAX_SIZE = 100;
		//final�� ���� ���ǰ��� ������ �ʴ´�.
		System.out.println(MAX_SIZE);
		
		final double AA = 200;
		System.out.println(AA);
		
		final String c = "����Ұ�";
				System.out.println(c);
		
		float result = (float) (3.3 + 4.5);
		//float result = (float)<-���� ��ȯ�Ͽ� ��갡�� (3.3 + 4.5);
		System.out.println(result);
		
		System.out.printf("������ %d �Ǽ��� %f" ,10,20.33);
		//printf�� �ٲ��ش�.(������� �ٹٲ��� ����.)
		//%d���� ������, %f���� �Ǽ��� �־��ش�.
		
		
		System.out.println(455623121234564564L);
		//L�� ���󱸿�!?
		
		System.out.println(3.4*1000000000);
		//������� 3.4E9�� ǥ���ȴ�.
	}
}
