package ex06;

import java.util.Iterator;

/* �ݺ��� �ȿ� �ݺ���
 * i = 0~2 �� �� j�� 0~2���� �ݺ��ض�
 */
public class ex01 {
	public static void main(String[] args) {

		for (int i = 0; i < 3; i++) {

			for (int j = 0; j < 3; j++) {

				System.out.println("i = " + i + " j = " + j);
			}
		}
//		System.out.println("j = " + j);
//		j���� i���� ���ϴ� for���� �ȿ� ����ֱ� ������ j���� ������� �ʴ´�.
	}
}
