package multi_Threading;

public class C_Thread_yieldJoinSleep {

	public static void main(String[] args) throws InterruptedException {
	
/*
 * we can prevent a thread execution by using the following methods:: yield(), join(), sleep()
 * 
 * >>> yield():: 
   ⭕ yield method causes to pass current executing thread to give the chance for remaining waiting threads 
      of same priority.if there is no waiting thread or all waiting threads have low priority then same 
      thread can continue it's a execution.
   ⭕ If multiple threads are waiting with a same priority then which waiting thread will get the chance we
      can't expect, it depends on thread scheduler.
   ⭕ The thread which is yielded, when it will get the chance once again it depends on thread scheduler,
      and we caon't expect exactly.
      
   ⭕  Syntax ----->    public static native void yield();
     
   ⭕  when you call yield() it going to Running state to --------> Ready/Runnable state
 
 * In the below program if we are commenting line 128 then both thread will be executed simultaneously 
     and we can't expect which thread will complete first.
 * If we are not commenting line 128 then child thread always calls yield method because of that main thread
     will get a chance more number of times and the chance of completing main thread first is high.
 
 * 	Note:: some platforms won't provide proper support for yield() method.
 // *************************************************************************************
 
 * >>> join()::
 	⭕ If a thread wants to wait until completing some other thread then we should go for join() method.
 	    ex. if thread t1 wants to wait until completing t2 then t1 has to call t2.join(); 
 	    
 	⭕ If t1 executed t2.join() then immediately t1 will be entered into waiting state until t2 completes.
 	    Once t2 completes then t1 can continue it's execution.
 	    
 	public final void join()               throws InterruptedException
 	public final void join(long ml)        throws InterruptedException
 	public final void join(long ml,int ns) throws InterruptedException
 	
 	Note:: every join() throws interrupted Exception which is checked Exception hence compulsory we should
 	       handle this exception either by using try catch or by throws keyword otherwise we will get 
 	       compile-time error.
   
 * If we comment line no80 then both main and child threads executed simultaneously and we can't expect output.
 
 * If we are not commenting line 80 then main thread calls join method on child thread object, hence main
      thread will wait until completing child thread.
        => in this case output is child thread run first then parent thread run .
 *   see:: Case1 , Case2 , Case3 and Case4    
 * *************************************************************************************** 
  
 * >>> sleep()::
    ⭕ If a thread don't want to perform any operation for a particular amount of time then we should go
      for sleep() method. 
      
      public static native void sleep(long ml);  throws InterruptedException
      public static void sleep(long ml,int ns);  throws InterruptedException
      
  NOTE: Every sleep() method throws InterruptedException, which is checked exception hence whenever we 
          are using sleep() method compulsory we should handle interruptedException either by try catch
          or by throws keyword otherwise we will get compile time error.   
 *    
 *    
 */

		myThread99 t = new myThread99();
		t.start();
		
		for(int i=0;i<10;i++) {
			System.out.println("main thread");
		}
// *****************************************************************************************
		
//		Case1:: waiting of main thread until completing child thread.

		For_join jo = new For_join();
		jo.start();   // child thread
		jo.join();  
		
		for(int i=0;i<10;i++) {
			System.out.println("execution in parent " + i);
		}
		
		
//		Case2:: waiting of child thread until completing main thread.
		
		CaseSecond.th = Thread.currentThread();
		
		CaseSecond seconde = new CaseSecond();
		seconde.start();
		
		for(int i=0;i<10;i++) {
			System.out.println("Parent thread in main class =======");
			Thread.sleep(1000);
		}
		
// * In the above example child thread calls join method on main thread object, hence child thread has to
//	 wait until completing main thread. o/p is :: first main thread run and then after child thread run.
		
		
//  Case3:: if main thread calls join() method on child thread object and child thread calls join() method
//		    on main thread object then both threads will wait forever and the program will be stucked 
//		    this is something like deadlock.
		
//  Case4:: if a thread calls join() method and the same thread itself then the program will be stucked
//		    (this is something like deadlock) in this case thread has to wait infinite amount of time.
		
//		Thread.currentThread().join(); only this run in main method
		
//************************************************************************************************
		// for sleep() method ==>> 
		for(int i =1;i<=10;i++) {
			System.out.println("Slide--- " + i);
			Thread.sleep(500);  // throws InterruptedException
		}
		
		
		
	}
}

class myThread99 extends Thread{
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("child method");
			Thread.yield();
		}
	}
}

// case 1
class For_join extends Thread{
	public void run() {
		for(int i=0;i<10;i++) {
			System.out.println("printing execution in child " + i);
		  try {  Thread.sleep(1000);    }
		  catch(Exception e) {
			  System.out.println(e.getMessage());
		  }
		}
	}
}

// case 2
class CaseSecond extends Thread{
	
	static Thread th;
	
	public void run() {
		
		try {
			th.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i=0;i<10;i++) {
			System.out.println("Child Thread in run method-----");
		}
	}	
}


