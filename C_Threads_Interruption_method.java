package multi_Threading;

public class C_Threads_Interruption_method {

	public static void main(String[] args) {
		
/*
 * ⏺️ Killing threads in Java >> 
         A thread is automatically destroyed when the run() method has completed. But it might be required
          to kill/stop a thread before it has completed its life cycle. Previously, methods suspend(),
          resume() and stop() were used to manage the execution of threads. But these methods were
          deprecated by Java 2 because they could result in system failures.
         Modern ways to suspend/stop a thread are by using a boolean flag and Thread.interrupt() method.
 * 
 * ⏺️ Using a volatile boolean flag:: 
         see the C_Volatile_keyword.java file
          
 * ⏺️ Using Thread.interrupt() method: 
         Whenever an interrupt has been sent to a thread, it should stop whatever task it is performing. 
         It is very likely that whenever the thread receives an interrupt, it is to be terminated.
         This action can be done by using the interrupt() method.  
    A thread can interrupt a sleeping thread or waiting thread by using interrupt() method of Thread Class
   
         syntax ----->    public void interrupt()
    
 *⭕ If we comment line 50 then main thread won't interrupt child thread in this case child thread execute
       for loop 10 times.
  ⭕ If we are not commenting line 50 then main thread interrupts a child thread in this case output is::
      -->> End of main thread
           I am lazy thread 
           i am interrupted   
           
 *Note: whenever we calling interrupt() method if the target thread not in sleeping state or waiting state
         then there is no impact of interrupt call immediately, interrupt call will be waited until target
          thread entered into sleeping or waiting state.
         If the target thread enter into sleeping or waiting state then immediately interrupt call will 
          interrupt target thread.    
       
 *  If the target thread never entered into sleeping or waiting states in it's lifetime then there is
    no impact of interrupt call this is the only case where interrupt call will be wasted.    
  
 * 
  
 */

	Demo88 t = new Demo88();
	t.start();
	
	//in the below ex interrupt call waited until child thread completes for loop 10 times. 
	t.interrupt();
//	----------------------------------------------------------------------------	
	Sleeping_thread th = new Sleeping_thread();
	th.start();
	
	th.interrupt(); // wasted
	
// ***********************************************************	
	 // creating objects t1 of My_Thread_in
	
	My_Thread_in t1 = new My_Thread_in();
	
	try {
		Thread.sleep(1);
		
		// t1 is an object of My_Thread_in which has an object t which is of type Thread
		t1.t.interrupt();
		
		Thread.sleep(4);
	}
	catch(InterruptedException e) {
		System.out.println("Caught:: " + e);
	}
	// Note: The output may vary every time. 
	
	System.out.println("Existing the main thread");
	System.out.println("End of main Thread....");
	}
}

class Demo88 extends Thread{
	
	public void run() {
		try {
				for(int i=1;i<=10;i++) {
			     System.out.println("I am lazy thread....");
				 Thread.sleep(2000);
	        	    }
				System.out.println("___________________Demo88_________________________");
		    } catch (InterruptedException e) {
				System.out.println("i am interrupted lazy thread Demo88 only.");
			}
		}
	}

class Sleeping_thread extends Thread{
	public void run() {
		for(int i=1;i<10;i++) {
			System.out.println("not sleeping");
		}
		System.out.println("---------------------Sleeping_thread-------------------------");
	}
}

//Java program to illustrate stopping a thread using the interrupt() method
class My_Thread_in	extends Thread{
	Thread t;
	My_Thread_in(){
		t = new Thread(this);
		System.out.println("New thread: " + t);
		t.start();  // starting the thread
	}
	  // execution of thread starts from run() method
	public void run() {
		while(!Thread.interrupted()) {
			System.out.println("Thread is runnnig....");
		}
		System.out.println("Thread has stopped.");
	}
}




