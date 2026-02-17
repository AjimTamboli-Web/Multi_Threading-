package multi_Threading.concurrency_Utilities_Tool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class A_Lock_interface {

	public static void main(String[] args) {

/*
 * The problems with the traditional synchronized keyword
    1>> we are not having any flexibility to try for a lock without waiting.
    2>> there is no way to specify maximum waiting time for a thread to get a lock so that thread will
        wait until getting the lock which may creates performance problems, which may cause deadlock.
    3>> if a thread releases the lock then which waiting thread will get that lock we are not having any
         control on this.
    4>> there is no API to list out for waiting threads for a lock
    5>> the synchronized keyword compulsory we have to use either at method level or within the method
        and it is not possible to use across multiple methods.
        
 *  To overcome this problems some people introduced java.util.concurrent.locks package in 1.5 version
    it also provides several enhancements to the programmer to provide more control and concurrency.
    
 * Lock interface::
    lock object is similar to implicit lock acquire by a thread to execute synchronized method and 
    synchronized block.
    lock implementation provide more extensive operations than traditional implicit locks.
    
 * 	Important methods of Lock interface::
   ⭕ void lock() - we can use this method to acquire a lock, if a lock already available then immediately
          current thread will get that lock, if lock is already not available then it will wait until get
          in the lock. it is exactly same behavior of traditional synchronized keyword.
    
   ⭕ boolean tryLock() - to acquire the lock without waiting , if the lock is available then the thread 
                 acquire the lock and returns true, if the lock is not available then this method returns
                 false and can continue it's execution without waiting is this case thread never be enter
                 into waiting state.
   
   ⭕ boolean tryLock(long time,TimeUnit unit) - if a lock is available then the thread will get the lock
                                                 and continue it's execution.
          if the lock is not available then the thread will wait until specified amount of time, still if
          the lock is not available then thread can continue it's execution.
          
          TimeUnit is an enum present in java.util.concurrent package.
          
   ⭕ void lockInterruptibly() - acquires the lock if it is available and returns immediately.
                      if the lock is not available then it will wait, while waiting if the thread is 
                       interrupted then thread won't get the lock.
                       
   ⭕ void unlock() - to call this method compulsory current thread should be owner of the lock,
            			otherwise we will get RE: IllegalMonitorStateException.          			
   
 */
		Rent r = new Rent();	
		ThreadMy my = new ThreadMy(r,"John");
		ThreadMy my2 = new ThreadMy(r,"Depp");
		my.start();
		my2.start();
	}
}

class Rent{
	Lock l = new ReentrantLock();
	
	public void geet(String name) {
		l.lock();
		for(int i =0;i<5;i++) {
			System.out.print("Good Morning: ");
           try {	
			Thread.sleep(1000);
           }catch(Exception ed) {
        	   ed.printStackTrace();
           }
           System.out.println(name);
		}
		l.unlock();
	}
}
class ThreadMy extends Thread{
	Rent r;
	String name;
	
	ThreadMy(Rent r,String name){
		this.r = r;
		this.name = name;
	}
	
	public void run() {
		r.geet(name);
	}
}
