package chapter03.use;

import chapter03.used.Calculator;
import chapter03.used.SubCalc;

public class Call {

	public static void main(String[] args) {
	// DIによって、注入元(new演算子での生成元)クラスを変更するだけで、「calculator.calc」の内容は自動的に切り替わってくれる
	// ⇒保守性が向上する
		
		// Calculator calculator = new AddCalc();
		Calculator calculator = new SubCalc();
		
		Integer result = calculator.calc(10, 5);
		System.out.println("結果: " + result);
	}
}
