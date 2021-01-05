package Section3;

public class JavaPractice6 {
	
	public static void main(String[] args) {
		String greeting = "Hello";
		String s = greeting.substring(1, 3); //第二个参数位及之后不要
		String s1 = greeting.substring(1); //保留第参数位及之后
		System.out.println(s);
		System.out.println(s1);
	}
}
