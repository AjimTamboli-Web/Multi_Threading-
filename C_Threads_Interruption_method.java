package multi_Threading;

public class C_Threads_Interruption_method {

	public static void main(String[] args) {
		
/*
 *  A thread can interrupt a sleeping thread or waiting thread by using interrupt() method of Thread Class
   
    syntax ----->    public void interrupt()
    
 * ⭕ If we comment line 35 then main thread won't interrupt child thread in this case child thread execute
       for loop 10 times.
   ⭕ If we are not commenting line 35 then main thread interrupts a child thread in this case output is::
      -->> End of main thread
           I am lazy thread 
           i am interrupted   
           
 * Note: whenever we calling interrupt() method if the target thread not in sleeping state or waiting state
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
	
	Sleeping_thread th = new Sleeping_thread();
	th.start();
	
	th.interrupt(); // wasted
	
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
		    } catch (InterruptedException e) {
				System.out.println("i am interrupted lazy thread Demo88 only.");
			}
		}
	}

class Sleeping_thread extends Thread{
	public void run() {
		for(int i=1;i<100;i++) {
			System.out.println("not sleeping");
		}
	}
}