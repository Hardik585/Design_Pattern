package _1_Singleton_DP.breaks;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class _2_Break_Using_Serializable {
	
	public static void main(String[] args) {
		try {
			FileOutputStream fos = new FileOutputStream("Student.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Student s1 = Student.getInstance();
			s1.sId=1;
			s1.name="Hardik";
			oos.writeObject(s1);
			
			FileInputStream fis = new FileInputStream("Student.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Student	 s2 = (Student) ois.readObject();
			System.out.println(s1== s2);  // false => During deserialization, 
										//	Java does not call your private constructor.
										// 	Instead, it creates a new object from the serialized byte stream.
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		try {
			FileOutputStream fos = new FileOutputStream("Student2.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			Student2 s1 = Student2.getInstance();
			s1.sId=1;
			s1.name="Hardik";
			oos.writeObject(s1);
			
			FileInputStream fis = new FileInputStream("Student2.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			Student2	 s2 = (Student2) ois.readObject();
			System.out.println(s1== s2); // true
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}

/*
 * this class will fail because : During deserialization, Java does not call
 * our private constructor. Instead, it creates a new object from the
 * serialized byte stream.
 */

class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Student instance;
	
	public int sId;
	public String name;
	
	
	//Constructor must be private 
	private Student() {
		
	}
	
	public static Student getInstance() {
		if(instance == null) {
			instance = new Student();
		}
		return instance;
	}
}

/*
* Solution: Use readResolve()
* 
* Java provides a special method for serialization to maintain Singleton
* behavior.
* 
* Add readResolve() method inside out Student class.
* 
* Why readResolve() works?
	During deserialization:
		Java creates a temporary object.
		It restores the object's state.
		If readResolve() exists, Java calls it.
		The object returned by readResolve() replaces the deserialized object.
*/


class Student2 implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Student2 instance;
	
	public int sId;
	public String name;
	
	
	//Constructor must be private 
	private Student2() {
		
	}
	
	public static Student2 getInstance() {
		if(instance == null) {
			instance = new Student2();
		}
		return instance;
	}
	
	private Object readResolve() {
	    return getInstance();
	}
}

