package Section3;

/**
 * @author Larry 3.5.6 关系运算符
 * 三元操作符?: 条件运算符
 */
public class JavaPractice5 {

	public static void main(String[] args) {
		int a = 1;
		int b = 2;
		if (a == b)
			System.out.println("a等于b");
		else if (a != b)
			System.out.println("a不等于b");

		if (a >= b && a / 0 == 1) 	//短路方式，第一个能判断结果，不考虑第二个
			System.out.println("yes");
		else 
			System.out.println("no");
		
		int x = a > b ? a : b;  //三元操作符 ?: 前面表达式true选第一个，false选第二个
		System.out.println(x);
	}

}
