package ex03;

//�������� 03
public class ex04 {

	public static void main(String[] args) {
		
//		���� ����ȯ �ʿ�.
//		int 4byte	short 2byte
//		8bit -> 1byte
		int num1 = 10;
		short num2 = (short) num1;

		
		
		
//		short 32000 -> ������ �ҽ��� �Ͼ �� ������ ��ȯ�� �ִ� ���
//		(����� ���������� �ʿ��ϴ�. ������->ū���� �ʿ����, ū��->������ �ʿ�.)
		System.out.println(num2);
		
//		�ڵ� ����ȯ.
		short num3 = 20;
		int num4 = num3;
		System.out.println(num4);
		
		
	}
	
}
