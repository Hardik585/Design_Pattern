package _1_Singleton_DP.breaks;

import java.lang.reflect.Constructor;

/*
 * In this class we are going to break the singleton DP 
 *	using the reflection API getConstructor Method 
*/
public class _1_By_Reflection_API {
    public static void main(String[] args) {
		try {
			Singleton s1 = Singleton.getInstance();
			Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			Singleton s2 = constructor.newInstance();
			System.out.println(s1 == s2 );  // false 
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		//with the avoid class 
		try {
			Singleton2 s1 = Singleton2.getInstance();
			Constructor<Singleton2> constructor = Singleton2.class.getDeclaredConstructor();
			constructor.setAccessible(true);
			Singleton2 s2 = constructor.newInstance(); // give excepton
			System.out.print(s1 == s2 );  // false 
		} catch (Exception e) {
			System.out.println(e.getCause().getLocalizedMessage());
		}
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

/*
 * To avoid the reflection api break we check whether the instance is null or
 * not inside the constructor
 * 
 *   However, this solution has a limitation
 *   our protection works only after the first instance is created.
 *   
 *   If reflection creates the first object directly, instance is still null, 
 *   so the constructor will not throw an exception.
 *   
 *   Best ways to make Singleton reflection-safe
 *   1. Enum Singleton (Recommended) 
 *   Java's enum singleton cannot be broken by reflection.
 */

class Singleton2{
	private static Singleton2 instance;
	
	//Constructor must be private 
	private Singleton2() {
		if(instance !=null) {
			throw new RuntimeException("use the getInstance() method");
		}
	}
	
	public static Singleton2 getInstance() {
		if(instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}

