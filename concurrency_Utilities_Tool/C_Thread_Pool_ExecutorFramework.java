package multi_Threading.concurrency_Utilities_Tool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionException;

public class C_Thread_Pool_ExecutorFramework {

	public static void main(String[] args) {
		
/*
 *⏺️  Executor Framework is a part of java.util.concurrent package introduced in Java 5 provides a 
        high-level API for managing thread execution. It lets developers submit tasks without manually 
        creating or controlling threads, as the framework handles scheduling and execution.
        Thread pool framework also known as Executor Framework.
 
 *⏺️  Key Components of the Executor Framework
       The Executor Framework consists of several core interfaces and classes that simplify
       concurrent programming in Java.
       
       1. Executor Interface  ->  This function executes the given command at some time in the future.
         The command may execute in a new thread, in a pooled thread, or in the calling thread, 
         at the discretion of the Executor implementation.    >> void execute(Runnable task)
         
       2. ExecutorService Interface -> The ExecutorService interface extends Executor by adding methods
        	    that help manage and control the execution of threads. It defines methods that provide an 
        	    implementation of the ExecutorService interface and many other interfaces, 
        	    with some default settings.    
        	    
       3. ScheduledExecutorService Interface -> ScheduledExecutorService Interface is extends
            ExecutorService and supports task scheduling, running tasks periodically or after a delay.
            
       4. ThreadPoolExecutor Class -> ThreadPoolExecutor is the most commonly used implementation of 
            ExecutorService. It manages a pool of worker threads to execute tasks efficiently, 
            reusing threads to reduce overhead.
              
       5. AbstractExecutorService Class -> A base class that provides default implementations for
            ExecutorService methods. Simplifies creating custom executors by handling common 
            functionalities like submit() and invokeAll().
       
       
 *    Creating a new thread for every job may creates performance and memory problems to overcome this
           we should go for thread pool.
      Thread pool is a pool of already created threads ready to do our job.
      Java 1.5 version introduces ThreadPool framework(Executor) to implement thread pools.
      Thread pool framework also known as Executor Framework.
   
 * We can create a ThreadPool as follows:
      ExecutorService ex = Executors.newFiexedThreadPool(3);
      
   We can submit a runnable job by using submit() method => ex.submit(job);
   we can shutdown ExecutorService by using shutdown() method => ex.shoutdown();
   
   >> In the below example three threads are responsible to execute six jobs so that a single thread can
       be reused for multiple jobs.
     
   NOTE:: While designing/developing web servers and applications servers we can use thread pool concept.
 * 
 */
	
		PrintJob[] jobs = { new PrintJob("Cleaning"),
							new PrintJob("Washing"),
							new PrintJob("Cooking"),
							new PrintJob("Running"),
							new PrintJob("Working"),
							new PrintJob("Typing") };
		
		ExecutorService ex = Executors.newFixedThreadPool(3);

		for(PrintJob j:jobs) {
			ex.execute(j);
		}
		ex.shutdown();
// ***************************************************************************************
		
		ExecutorImp obj = new ExecutorImp();
		try {
			obj.execute(new MyThreadExecu());
		}catch(RejectedExecutionException
				| NullPointerException exception) {
              System.out.println(exception);
		
		}	
	}

}

class PrintJob implements Runnable{
	
	String name;
	PrintJob(String name){
		this.name = name;
	}
	
	@Override
	public void run() {
		
		System.out.println(name + ".... job started by " + Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		}catch(Exception ex) {
			
		}
		System.out.println(name + " job completed by Thread:: " + Thread.currentThread().getName());
	}	
}
// **********************************************************************
// 2nd Example

class ExecutorImp implements Executor{

	@Override
	public void execute(Runnable command) {
		new Thread(command).start();
	}
}

class MyThreadExecu implements Runnable{

	@Override
	public void run() {
		System.out.println("Thread executed under a an MyThreadExecu class.......");
	}
	
}
