package ex04;

public class exam2 {

	/*
	 * �Ʒ��� ���� num�� ���� ���� '���', '����', '0'�� ����ϴ� �ڵ��̴�. ���� �����ڸ� �̿��ؼ� (1)�� �˸��� �ڵ带 �����ÿ�.
	 */
	
	public static void main(String[] args) {
		int num = -10;
		
//		����Ǯ��
//		Scanner scan = new Scanner(System.in);
//		int num = scan.nextInt();
//		System.out.println("num = "+num);
//		System.out.println(num > 0 ? " ����Դϴ�." : num == 0�Դϴ�. ? " 0" : " �����Դϴ�.");
//		
		
		if (num > 0) {
			System.out.println("���");
		} else if (num < 0) {
			System.out.println("����");
		} else {
			System.out.println("0");
		}
	}
}
