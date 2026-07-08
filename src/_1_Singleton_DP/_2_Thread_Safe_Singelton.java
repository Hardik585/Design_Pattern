package _1_Singleton_DP;

/* 
 * The first Singleton implementation uses Lazy Initialization, which means
 * the object is created only when it is needed.
 *
 * However, this implementation is not thread-safe. If multiple threads call
 * getInstance() at the same time, each thread may create its own object,
 * violating the Singleton Design Pattern, whose goal is to have only one
 * instance of the class.
 *
 * To overcome this issue, we can make the getInstance() method synchronized,
 * ensuring that only one thread can execute it at a time.
 */

public class _2_Thread_Safe_Singelton {
	
}

/*
 * Here, we use the synchronized keyword at the method level to make the
 * Singleton thread-safe.
 *
 * If multiple threads call getInstance() simultaneously, only one thread
 * can enter the method at a time. The first thread creates the Singleton
 * object, while the other threads wait.
 *
 * Once the first thread exits the method, the waiting threads enter one by
 * one and return the already created instance. Therefore, only one object
 * is created.
 *
 * The drawback of this approach is that every call to getInstance() is
 * synchronized, even after the Singleton object has already been created.
 * This introduces unnecessary synchronization overhead and can reduce
 * performance.
 */
class Singleton {
	
	private static Singleton instance;
	
	private Singleton() {
		
	}
	
	public static synchronized Singleton getInstance() {
		if(instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
}

/*
 * The below class implementation uses the Double-Checked Locking (DCL) technique.
 *
 * The first null check avoids entering the synchronized block once the
 * Singleton instance has already been created, improving performance.
 *
 * If the instance is null, the thread enters the synchronized block.
 * Inside the synchronized block, a second null check is performed because
 * another thread might have created the instance while the current thread
 * was waiting for the lock.
 *
 * This approach is thread-safe and reduces the synchronization overhead
 * compared to synchronizing the entire getInstance() method.
 *
 * Note: In Java, the instance variable should be declared as 'volatile'
 * to prevent instruction reordering and ensure that all threads see the
 * fully initialized object.
 */
class Singleton2 {
	
	private static volatile Singleton2 instance;
	
	private Singleton2() {
		
	}
	
	public static Singleton2 getInstance() {
		if(instance == null) {
			synchronized(Singleton2.class){
				if(instance == null) {
					instance = new Singleton2();		
				}
			}
		}
		return instance;
	}
}

