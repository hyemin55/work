package ex04;

/*
 * 아래의 코드는 사과를 담는데 필요한 바구니(버켓)의 수를 구하는 코드이다.
 * 만일 사과의 수가 123개이고 하나의 바구니에는 10개의 사과를 담을 수 있다면
 * 13개의 바구니가 필요할 것이다.
 * (1)에 알맞은 코드를 넣으시오.
 */
public class exam1 {

	public static void main(String[] args) {
		int numOfApples = 123;
		int sizeOfBucket = 10;
		int numOfBucket = (numOfApples / sizeOfBucket);
		int a = numOfApples % sizeOfBucket;

//		문제풀이
//		System.out.println((numOfBucket % sizeOfBucket > 0 ? 1 : 0));
//		int numOfBucket = numOfApples / sizeOfBucket + (numOfApples % sizeOfBucket > 0 ? 1 : 0);
//		System.out.println("필요한 바구니의 수 = " + sizeOfBucket );
		
		int result = a > 0 ? (numOfBucket + 1) : numOfBucket;
		System.out.println("필요한 바구니의 수 = " + result);
	}
}

