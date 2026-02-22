package multi_Threading.concurrency_Utilities_Tool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class B_ReentrantLock_implements_Lock {

	public static void main(String[] args) {
	
/*
 * ReEntrantLock - it is the implementation class of Lock interface and it is the direct child class
                   of Object class.
                   ReEnterant Means a thread can acquire same lock multiple times without ant issue,
                   internally reentrant lock increments threads personal count whenever we call lock()
                   and decrements count value whenever thread calls unlock() and lock will be released
                   whenever count reaches 0.

	Constructors::
	1>>  ReentrantLock l = new ReentrantLock() -> creates an instance of reentrant lock.
	        
	2>> ReentrantLock l = new ReentrantLock(boolean fairness) -> it creates reentrant lock with the 
																given fairness policy,
         if the fairness is true then longest waiting thread can acquired the lock if it is available 
         that is it follows FCFS(First Come First Serve) policy,
         if a fairness is false then which waiting thread will get the chance we can't expect.
         
         NOTE: the default value for fairness is false.
         
    ❓which of the following declarations are equal?
     ✔️ ReentrantLock l = new ReentrantLock();
     🟦 ReentrantLock l = new ReentrantLock(true); ❌
     ✔️ ReentrantLock l = new ReentrantLock(false);
     🟦 All the above ❌
     
 *  Important methods of ReentrantLock -
    void lock();
    boolean tryLock()
    boolean tryLock(long t, TimeUnit t)
    void lockInterruptibly()
    void unlock()
    
   ⭕ int getHoldCount() -> returns number of holds on this lock by current thread
    
   ⭕ boolean isHeldByCurrentThread() -> returns a true if and only if lock is hold by current thread
    
   ⭕ int getQueueLength() -> returns number of threads waiting for the lock
    
   ⭕ Collection getQueuedThreads() -> it returns a collection of thread which are waiting to get the lock
    
   ⭕ boolean hasQueuedThreads() -> returns a true if any thread waiting to get the lock
    
   ⭕ boolean isLocked() -> returns true if the lock is acquired by some thread
    
   ⭕ boolean isFair() -> returns true if the fairness policy is set true values
    
   ⭕ Thread getOwner() -> returns the thread which acquire the lock
    
    
         
 * 
 */
		
		ReentrantLock l = new ReentrantLock();
		
		l.lock();
		l.lock();
		
		System.out.println(l.isLocked()); // true
		System.out.println(l.isHeldByCurrentThread()); // true
		System.out.println(l.getQueueLength());   // 0
		l.unlock();
		System.out.println(l.getHoldCount()); // 1
		System.out.println(l.isLocked());  // true
		l.unlock();
		System.out.println(l.isLocked());  // false
		System.out.println(l.isFair());   // false

//	***********************************************************************************
		
		Display de = new Display();
		MyThread t1 = new MyThread(de,"Dom");
		MyThread t2 = new MyThread(de,"Hobbs");
		MyThread t3 = new MyThread(de,"Bran");
		
		t1.start();
		t2.start();
		t3.start();

// ************************************************************************************		
		System.out.println("________________________________________________");
		MyThread2 re1 = new MyThread2("First lock");
		MyThread2 re2 = new MyThread2("Second lock");
		
		re1.start();
		re2.start();
		
		System.out.println("________________________________________________");
// **********************************************************************************
		
		MyThread3 ty1 = new MyThread3("One ");
		MyThread3 ty2 = new MyThread3("Two");
		
		ty1.start();
		ty2.start();
		
		System.out.println("___________________________________________________");
		
	}

}
class Display{
	
	ReentrantLock l = new ReentrantLock();
	
	public void wish(String name) {
// if we comment a line 120 and 133 then the threads will be executed simultaneously and we get 
//		irregular output, if we are not commenting line then threads will be executed one by one and 
//		we will get regular output.
		
		l.lock(); // lock
		
		for(int i=0;i<10;i++) {
			System.out.print("Good Morning:: ");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(name);
		}
		
		l.unlock(); // unlock
	}
}

class MyThread extends Thread{
	Display d;
	String name;
	MyThread(Display d, String name){
		this.d = d;
		this.name = name;
	}
	
	public void run() {
		d.wish(name);
	}
}


// this example for tryLock method implementation
class MyThread2 extends Thread{

	static ReentrantLock l = new ReentrantLock();
	
	MyThread2(String name){
		super(name);
	}
	
	public void run() {
		if(l.tryLock()) {
			System.out.println(Thread.currentThread().getName() + " ......got lock and performing "
					+ "safe operations");
			try {
				Thread.sleep(2000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			l.unlock();
		}
		else {
			System.out.println(Thread.currentThread().getName() + " ..unable to get lock perform "
					+ "alternative operations.");
		}
	}
}

//*****************************************************************
class MyThread3 extends Thread{
	static ReentrantLock l = new ReentrantLock();
	
	MyThread3(String name){
		super(name);
	}
	
	public void run() {
		do {
			try {
				if(l.tryLock(2000, TimeUnit.MILLISECONDS	)) {
					System.out.println(Thread.currentThread().getName() + "..... got lock");
					Thread.sleep(10000);
					l.unlock();
					
					System.out.println(Thread.currentThread().getName() + "..release lock	");
					break;
				}
				else {
					System.out.println(Thread.currentThread().getName() + "unable to get lock and "
							+ "try again!");
				}
			}
			catch(Exception ed) {
				ed.printStackTrace();
			}
		}while(true);
	}
}