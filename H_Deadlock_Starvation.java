package multi_Threading;

public class H_Deadlock_Starvation {

	public static void main(String[] args) {
		
/*
 * If two threads are waiting for each other forever, such type of infinite waiting is called deadlock.
 * 
 * Synchronized keyword is the only reason for deadlock situation hence while using synchronized keyword 
 		we have to take special care ,
   There are no resolution technique for deadlock but several prevention techniques are available.
 
 *  	In the below program if we remove at least one synchronized keyword then the program won't enter into
    deadlock, hence synchronized keyword is the only reason for deadlock situation, so due to this while
    using synchronized keyword we have to take special care.	
 
 * ⏺️ Starvation
 *    DeadLock vs Starvation:: 
      Long waiting of a thread where waiting never ends is called DeadLock
      whereas long waiting of a thread where waiting ends at a certain point is called starvation.
      ex. lowPriority Thread has to wait until completing all highPriority Threads it may be long waiting
       but ends at certain points, which is nothing but starvation. 		
 		
 */
			
		 DeadLock1 dead = new DeadLock1();
//		 dead.start(); // instead of using this, we use m1() because main method is static method,and in 
//		             static method we cannot access direct instance variable,so we use instance method.
		 dead.m1();  // deadlock
		 
		// starvation
		 //  when child thread complete at certain time then main thread got chance to execute that same
//		     resource that's a starvation.
		 dead.setPriority(10); // child thread
		 Thread.currentThread().setPriority(1);  // main thread  - starvation 
		 
		 
	}
}

class A{
	public synchronized void d1(B b) {
		System.out.println("Thread 1 starts execution of d1() method.");
	try {
		Thread.sleep(1000);
	}catch(InterruptedException ex) {
		
	}
	System.out.println("Thread 1 is trying to call B's last()");
	b.last();
	}
	public synchronized void last() {
		System.out.println("Inside A, this is last() method");
	}
}
class B{
	public synchronized void d2(A a) {
		System.out.println("Thread 2 starts execution of d2() method.");
		try {
		Thread.sleep(1000);
		}catch(InterruptedException ex) {
			
		}
		System.out.println("Thread 2 is trying to call A's last()");
		a.last();
	}
	public synchronized void last() {
		System.out.println("Inside B, this is last() method");
	}
}
class DeadLock1 extends Thread{
	A a = new A(); // instance variable
	B b = new B(); // instance variable
	public void m1() {
		this.start();
		a.d1(b);    // this line is executed by main thread
	}
	public void run() {
		b.d2(a);   // this line is executed by child thread
	}
}