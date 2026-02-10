package multi_Threading;

public class A_Multi_Threading_Overview {

      public static String vari = "John";
	public static void main(String[] args) throws InterruptedException {
	
/*
 * Introduction :: 
 * Multitasking = executing several tasks simultaneously is a concept of multitasking
 * there are two types of multitasking 
 * a> Processed based multitasking 
 * b> Thread based multitasking
  
 *A)  Process Based Multitasking => Executing several tasks simultaneously where each task is a separate 
                                independent program(process) is called processed based multitasking.
 * ex. while typing in java program in the editor we can listen audio songs from the same system at the same 
       time we can download a file from net.all this a tasks will be executed simultaneously and independent 
           of each other hence it is process based multitasking.
        
 *       process based multi-tasking is a best suitable at OS level.
        
 *B)  Thread Based Multitasking => Executing several tasks simultaneously where each tasks is a separate 
                                   independent part of the same program is called thread based multitasking
                                    and each independent part is called a Thread.       
 *       Thread based multitasking is best suitable at programmatic level.
  
  
 * Whether it is process based or thread based the main objective of multitasking is to reduce response time
    of the system on the to improve performance.
  
 
 * The main important application area of multiThreading is:: 
  	1.to develop multiMedia graphics
  	2.to develop animations
  	3.to develop video games
  	4.to develop web servers and applications servers etc
  
  
 * When compared with a old languages developing multi-threaded applications in java is very easy because
   java provides inBuild support for multi-threading with reach API(thread API classes = 
   																			Thread,runnable,thread group)
   
 * 
 * The ways to define a Thread =>>  a> By extending Thread class    b> By implementing Runnable Interface
  
 * Getting and setting Name of Thread
 * Thread Priority
 * The Methods the prevent Thread Execution
    a> yield()  b> join()  c> sleep()
 * Synchronization
 * Enter Thread Communication
 * Dead Locks
 * Daemon Thread
 * MultiThreading Enhancement		
 * (all above topics we see one by one in this folder)
 * 
 */
		
		Cu_thread th = new Cu_thread();
		
		th.start(); // child thread created

		System.out.println(vari);
		
		int count = 0;
		System.out.println(Thread.currentThread().getName());
		
		while(count < 10) {
			Thread.sleep(700);
			System.out.println(count + " main thead" );
			count++;
		}
		

	}

}
/*
 * Multi-threading in Java is feature that allows multiple tasks to run concurrently within the same program.
 * Instead of executing one task at a time, Java enables parallel execution using lightweight threads.
 * This makes applications more efficient, faster and responsive in real-world scenarios like servers,
     games and chat systems.
 *  A thread is a lightweight, independent unit of execution inside a program (process).
    ⭕  A process can have multiple threads.
	⭕  Each thread runs independently but shares the same memory. 
	 
 */
class Cu_thread extends Thread{
	
	public void run() {
		System.out.println(Thread.currentThread().getName());
		
		// below code called job 
		for(int i =1;i<10;i++) {
			try {
				Thread.sleep(700);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i + " child thread");
		}
	}
	
	
}
