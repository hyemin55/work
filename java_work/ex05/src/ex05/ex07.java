package ex05;

public class ex07 {

	public static void main(String[] args) {
		
//		while���� num�� 1�� ������ �� if���� 5�� �������� 0�� �ƴϸ� 
//		continue;������ �ٽ� ���ǰ˻�� �̵��ϴ°� �ݺ��ϰ�
//		if���� 5�� �������� 0�� �����ϸ� �����ٷ� �Ѿ ����� �ȴ�.
		
		int num = 0;
		while((num++)<100) {
			if(num%5 != 0 || num%7 !=0)
				continue;
			System.out.println("num = "+num);
		}
		
	}
}
