package ex06;

public class ex07 {

	
	public static void main(String[] args) {
		int result = doA(3);
		String temp = doB("�ȳ��ϼ���");
		System.out.println(result);
		System.out.println(temp);
		
		System.out.println(doA(3));
		System.out.println(doB("�ȳ��ϼ���"));
	}
	
	public static int doA(int num) {
		return num * num;
	}
	
	public static String doB(String str) {
		return str + str;
	}
}
