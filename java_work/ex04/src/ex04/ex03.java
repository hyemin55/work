package ex04;

public class ex03 {
	
	public static void main(String[] args) {
		
		int num1 = 10;
		
//		num++
//		num = num+1
//		num += 1
//		�� 3���� ��� ���� ǥ�����.
		
		System.out.println(num1++);
//		num1 ����ϰ� ++ �����ؼ� ���Ⱚ�� 10�� ������ �� ������ +1�� ���� ���� ���´�.
		System.out.println(num1);
		System.out.println(++num1);
		
		System.out.println(num1);

		System.out.println(num1--);
//		num1 ����ϰ� -- �����ؼ� ���Ⱚ�� 12�� ������ �� ������ -1�� �� ���� ���´�.
		System.out.println(num1);
		System.out.println(--num1);
		
		System.out.println(num1);

		for (int i = 0; i <= 10; i++)
			{System.out.println(i);
			System.out.println("GGG");
			System.out.println(i++); //i+=1 or i=i+1
			
			}
		
//		for ������ ������ �� ���� {}�ȿ� ����ִ� ���� �ݺ����ش�.
	}
}

/*	����� ���(�������)
 * 	�극��ũ ����Ʈ -> ���ϴ� ���� ��ȣ ���ʿ� ����Ŭ���Ͽ� ����ȣ ������ ����.
 * 	����������� ����
 * F6 ���پ� ����
 * F8 ����
 * */
