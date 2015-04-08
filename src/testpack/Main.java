package testpack;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		for(int i = 1; i <= 10; i++){
			if((i % 2) != 0) continue;
			System.out.printf("%d ",i);
		}
		System.out.println();
	}

}
