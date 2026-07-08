package _1_Singleton_DP;

/*Lazy Implementation: object is created when required*/
public class _1_Basic_Singleton {
  
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);
	}
}


class Singleton{
	
	private static Singleton instance;
	
	//Constructor must be private 
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}

