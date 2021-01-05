package Section3;

/**
 * @author Larry
 *3.5.1 运算符和常见数学函数的使用
 */

public class JavaPractice2 {

	public static void main(String[] args) {
		int i = -4;
		double d = 15.0;
		System.out.println("整数除法："+i/2);
		System.out.println("浮点除法："+i/2.0);
		System.out.println("浮点除法："+d/2);
		System.out.println("取余："+i%3);
		System.out.print("\n");
		
		System.out.println("0除以整数："+0/i);
		System.out.println("0除以小数："+0/d);		
		System.out.println("小数除以0："+d/0);
//		System.out.println("整数除以0："+i/0); //异常报错
		System.out.print("\n");
		
		int x = 9;
		System.out.println("平方根Math.sqrt(x)："+Math.sqrt(x)); //返回double类型
		System.out.println("乘幂Math.pow(x，a)："+Math.pow(x, 2)); //返回double类型
		
		
		System.out.println("取模Math.floorMod(A，B)："+Math.floorMod(-4, 3)); //取模
		
		System.out.println("PI："+Math.PI); 
		System.out.println("E："+Math.E); 
	}

}
