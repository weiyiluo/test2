package Section3;

/**
 * @author Larry
 * 赋值运算符
 * 5.3.4 +=
 * 5.3.5 x++  --x
 */
public class JavaPractice4 {

	public static void main(String[] args) {
		int x = 1;
		x += 4;
		System.out.println(x);
		x *= 5;
		System.out.println(x);
		x -= 10;
		System.out.println(x);
		x /= 5;
		System.out.println(x);
		x %= 2;
		System.out.println(x);

		x += 4.5;
		System.out.println(x);

		x++;
		System.out.println(x);
		++x;
		System.out.println(x);
	}

}
