package ex05;

public class ex06 {

	public static void main(String[] args) {
		
//		3, 4�� �ּҰ����
//		3�� 4�� ���������� �ߺ����� 0���� �������� ��.
		
//		break�� swith �Ǵ� while for ������ Ż���Ѵ�.
//		continue�� �ٽ� ���ǰ˻�� �̵��Ѵ�.
				
		for (int num = 1; num < 100; num++) {
			
			if(num % 3==0 && num % 4==0 ) {
			System.out.println("num="+num);
			break;
		}
		}
	}
}
