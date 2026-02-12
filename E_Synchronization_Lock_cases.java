package multi_Threading;

public class E_Synchronization_Lock_cases {

	public static void main(String[] args) {
	
/*
 * Case Study:: 
     Even though print() method is synchronized we will get irregular output because threads are operating 
      on different java objects.
  ⭕ Conclusion  => if multiple threads are operating on same java object then synchronization is required,
      if multiple threads are operating on multiple java objects then synchronization is not required.    
      
  ⭕ Class level Lock ::
      Every class in java has a unique lock which is nothing but class level lock.
      If a thread wants to execute a static synchronized method then thread required class level lock.
      Once thread got class level lock then it is allowed to execute any static synchronized method of that
           class.   Once a method execution complete automatically thread release the lock.
      
 * While a thread executing static synchronized method the remaining threads are not allowed execute
   any static synchronized method of that class simultaneously but remaining threads are allowed to execute
   the following methods simultaneously ::   1) normal static methods 2) synchronized instance method
    3) normal instance method
 * 
 * 
 * 
 * 		
 */
		
			Source_I sours = new Source_I();
			Source_I sours2 = new Source_I();
			Imple_thread t1 = new Imple_thread(sours,"Jackob");
			Imple_thread t2 = new Imple_thread(sours2,"sara");
			t1.start();
			t2.start();
			
			// if class level lock executed by any thread below run simultaneously with it.
			Norm t3 = new Norm(sours);
			Stati t4 = new Stati(sours);
			SynchMeth t5 = new SynchMeth(sours);
			t3.start();
			t4.start();
			t5.start();
			
	}

}
class Source_I {
//	public synchronized void print(String name) throws InterruptedException { // object level lock
	public static synchronized void print(String name) throws InterruptedException { // class level lock
		
		for(int i=0;i<10;i++) {
			System.out.print("Good Morning:: ");
			Thread.sleep(1000);
			System.out.println(name);
		}
	}
	public synchronized void syn() throws InterruptedException {  // another thread can access no problem
		Thread.sleep(500);
		System.out.println("This is synch instance method");
	}
	public void norma() throws InterruptedException {  // another thread access
		Thread.sleep(100);
		System.out.println("This is instance method");
	}
	public static void stat() {                         // another thread can access
		System.out.println("This is normal static method only");
	}
}
class Imple_thread extends Thread{
	Source_I sou;
	String name;
	Imple_thread(Source_I i, String name){
		this.name = name;
		this.sou = i;
	}
	public void run() {
		try {
			Source_I.print(name);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Norm extends Thread{
	Source_I f;
	Norm(Source_I k){
		this.f = k;
	}
	public void run() {
		try {
			f.norma();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
class Stati extends Thread{
	Source_I f;
	Stati(Source_I k){
		this.f = k;
	}
	public void run() {
		Source_I.stat();
	}
}
class SynchMeth extends Thread{
	Source_I f;
	SynchMeth(Source_I k){
		this.f = k;
	}
	public void run() {
		try {
			f.syn();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}