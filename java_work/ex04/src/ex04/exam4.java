package ex04;

public class exam4 {

/*
 * �Ʒ��� ȭ��(F)�� ����(C)�� ��ȯ�ϴ� �ڵ��̴�. ��ȯ������ 'C = (F-32)/1.8' ��� �� �� (1)�� �˸��� �ڵ带 �����ÿ�.
 *  ��, ��ȯ ������� �Ҽ��� ��°�ڸ����� �ݿø��ؾ� �Ѵ�.(����ȯ�̿�)
 */
	public static void main(String[] args) {
		int F = 100;
		float C = (float) ((F - 32) / 1.8);

		int a = (int) (C *100);
		
		float c = ((a%10)>=5) ? (float)(a+1)/100 : (float)a/100;
		
		System.out.println("F = " + F);
		System.out.println("C = " + c);
//		System.out.println("C = " + Math.round(C));
//		System.out.println(((int)(C*100))/(double)100);
		
	}
}

//package ex001;
//
////�Ʒ��� ȭ��(F)�� ����(C)�� ��ȯ�ϴ� �ڵ��̴� ��ȯ������
////[C] = ([F]-32) / 1.8 ��� �Ҷ�
////(1)�� �˸��� �ڵ带 �����ÿ�. ��, ��ȯ ������� �Ҽ��� ��°�ڸ����� �ݿø��ؾ� �Ѵ�.
//class exam04{
// public static void main(String[] args) {
//    int F = 100;
//    
//    double fc = (F-32)/1.8;
////    System.out.println(fc);
//    int cc = (int) (fc*1000); // ��° �ڸ����� ����� ����(37777) 
//    int ABC = cc%10; // �ݿø� if ���� ���� ��
//    
//    int cc2 = (int) (fc*100);
//    double C = (double) cc2/100;
//    double B = 0.01;
//    
//    if(5<=ABC) {
//       System.out.println("F = " + F);
//       System.out.println("C = " + (C+B));
//       System.out.println("�÷�");
//    }
//    else {
//       System.out.println("F = " + F);
//       System.out.println("C = " + C);
//       System.out.println("����");
//    }

// }
//}