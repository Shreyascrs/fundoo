package lambdapackage;

import java.util.function.Predicate;

public class Test {

	public static void main(String[] args) {
//		Testlambda t=(int a,int b) ->{System.out.println(a*b);};
//		Testlambda t1=(a,b) ->{System.out.println(a+b);};
//		t.add(10, 10);	
//		t1.add(10, 10);
		
		Predicate<Integer> p=number->(number%10 == 0) ;
		System.out.println(p.test(10));
		System.out.println(p.test(11));
	}
}
