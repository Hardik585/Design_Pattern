package _1_Singleton_DP.breaks;

public class _3_Break_Using_Clone {
	public static void main(String[] args) {

		// Breaking Singleton using clone()
		try {
			Singleton s1 = Singleton.getInstance();

			Singleton s2 = (Singleton) s1.clone();

			System.out.println(s1 == s2); // false
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Solution using overridden clone()
		try {
			Singleton2 s1 = Singleton2.getInstance();

			Singleton2 s2 = (Singleton2) s1.clone();

			System.out.println(s1 == s2);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}



class Singleton implements Cloneable {
	private static Singleton instance;

	// Constructor must be private
	private Singleton() {

	}

	public static Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

// Solution to the above is override the clone method and throw error 

class Singleton2 implements Cloneable {

	private static Singleton2 instance;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException("use the getInstance()");
	}

	// Constructor must be private
	private Singleton2() {

	}

	public static Singleton2 getInstance() {
		if (instance == null) {
			instance = new Singleton2();
		}
		return instance;
	}
}
