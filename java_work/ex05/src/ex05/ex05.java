package ex05;

import java.util.Scanner;

public class ex05 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		int n = 0;
		
		while(true) {
			
			n++;
			System.out.println("n="+n);
			if(n==3)
				continue;
//			continue�� ���ǿ� �����ϸ� �������� ���� �����ʰ� ���� �˻�� �̵��Ѵ�.
			
			int temp = scan.nextInt();
			System.out.println("temp = "+temp);
		}
		
//		System.out.println("����˴ϴ�.");
	}
}
