package chapter02;

import java.util.ArrayList;
import java.util.List;

public class ListExercise {

	public static void main(String[] args) {
		List<String> prefecture = new ArrayList<>();
		prefecture.add("北海道");
		prefecture.add("沖縄県");
		prefecture.add("香川県");
		for(String local_name : prefecture) {
			System.out.println(local_name);
		}
		
		// ジェネリクスのおかげで、String型以外の値の追加は制限される
		// prefecture.add(2);
	}

}
