package Section3;
/**
 * @author Larry
 *3.5.2 不同类型数值运算  类型转换
 *3.5.3 强制类型转换
 */
public class JavaPractice3 {

	public static void main(String[] args) {
		double d = 1.0;
		float f= 2.0f;
		long l = 1000000000000l;
		int i = 22;
		
		System.out.println(d+f);
		System.out.println(d+l);
		System.out.println(d+i);
		System.out.print("\n");
		
		System.out.println(f+l);
		System.out.println(f+i);
		System.out.print("\n");
		
		System.out.println(l+i);
		System.out.print("\n");
		
		//强制类型转换
		double d1 = 9.49;
		int i1 = (int)d1; //舍弃小数
		System.out.println(i1);
		int i2 = (int)Math.round(d1); //四舍五入
		System.out.println(i2);
	}

}
