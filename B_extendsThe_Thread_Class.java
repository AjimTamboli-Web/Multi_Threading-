package multi_Threading;

public class B_extendsThe_Thread_Class {

	public static void main(String[] args) throws InterruptedException {
/*
 * Note: Extend Thread when when you don’t need to extend any other class. Implement Runnable when your
          class already extends another class (preferred in most cases).
          
 * Define a Thread = :: 
      We can define a thread in the following two ways 
      1) by Extending thread class
      2) by implementing runnable interface
      
 *  1> by Extending Thread class =>>
  
 *    Case 1:: 
     Thread Scheduler ::  ⭕	it is a part of JVM 
         ⭕ it is a responsible to schedule threads that is if multiple threads are waiting to get the chance
      	           of execution then in which order threads will be executed is decided at thread scheduler.
         ⭕ we can't expect exact algorithm followed at thread scheduler it is waryed from JVM to JVM hence 
              we can't expect thread execution order and exact output.
         ⭕  Hence whenever situation comes to multi-threading there is no guarantee for exact output but,
               we can provide several possible outputs.
 *    Case 2::         
       Difference between t.start() and t.run() ::
     	⭕ in the case of t.start() a new thread will be created which is responsible for the execution of run method
        but in the case of t.run() a new thread won't be created and the run() will be executed just like a normal
        method call by main thread  hence in the below program if we replace t.start with a t.run then the output
        is JVM flow, this total output produce by only main thread.
 		
 *  Case 3:: 
     Importance of thread class start()
      ⭕ Thread class start() method is responsible to register the thread with thread scheduler and all other mandatory 
      activities hence without executing thread class start method there is no chance of starting a new thread in 
      java due to this thread class start method is consider as heart of multi-threading.
        				start() 
        				{ 	1. Register this thread with Thread Scheduler
        				  	2. Perform all other mandatory activities
        				  	3. Invoke run();
        			    }	  
 *  Case 4:: 
 	 Overloading of run()
 	  ⭕ Overloading of run() is always possible but, thread class start() method can invoke no argument run method the other 
 	     overloaded method we have to call explicitly like a normal method call.
 	  
 *	Case 5::
 	 If we are not overriding run() method
 	   ⭕ then thread class run() method will be executed which has empty implementation hence we won't get 
 	    any output.  (no output)
 	     NOTE:: it is highly recommended to override run() method otherwise don't go for multi-threading concept

 * 	Case 6::
 	Overriding of start() method
 	 ⭕ if we override start() then our start() will be executed just like a normal method call 
 	    and new thread won't be created.   (output produced by only main thread)
    NOTE::  it is not recommended to override start() method otherwise don't go for multi-threading concept.
 	
 *  Case 7::
 	  Overriding start method in super call to Thread class in start()
 	   Main thread -> start() override method in custom class	
 	   Start new thread -> call thread run() statement in new thread
 		
 * Case 8:: 
  	  Thread life cycle	
  	                  						(if T.S allocated processor)	
  	    New/BORN  ------------>>> Ready/Runnable  --------------> Running ------------------------->>> Dead
 (myThread t1 = new myThread())   (t1.start)                    			 (if run() method completes)
  	   
 * Case 9::   
  	  After starting a thread if we are trying to restart the same thread then we will get runtime exception
  	   saying IllegalThreadStateException .
  	   Ex:: t9.start();  first time call thread no problem
  	        t9.start();   second time restart the same time we get exception
  	
 *  
 */
		
		Custom t1 = new Custom();    // Thread instantiation
		Custom t2 = new Custom();
		
		t1.setName("Parent");  // set the name of threads
		t2.setName("Child");   // set the name of threads
		
		// Case 1: 
		// Thread scheduler -  we can't expect thread execution order and exact output.
		t1.start();   // Starting of a Thread
		t2.start();
	
		Case2 t3 = new Case2(); // difference between run() and start()
		t3.start(); // created new thread
		t3.run();   // normal method called by instance and run by main thread
		
		Case4 t4 = new Case4();
		t4.start(); // invoke only no argument run() method new thread created
		t4.run(23); // normal overload method, no new thread created run by main thread
		
		Case5 t5 = new Case5(); // Constructor always runs in calling thread, not in new thread
		t5.start(); // start() creates a new thread and calls run(), If you don’t override run(), thread does nothing
	
		Case6 t6 = new Case6();
		t6.run();
		t6.start(); // normal method call, thread won't be created nor run() call internally
		
		Case7 t7 = new Case7();
		t7.start(); // running run() in new thread and start() in main thread
		
		Case9 t9 = new Case9();
		t9.start();
		// if we trying to restart a same thread again we will get RunTime exception
//		t9.start(); // IllegalThreadStateException
		
		
	}

}

class Custom extends Thread{
	public void run() {		
		// Job of thread 
		for(int i=1;i<5;i++) {
			System.out.println(Thread.currentThread().getName());		
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(i);		
		}			
	}	
}

class Case2 extends Thread{
	public void run() {
		System.out.println("case2 run() method called by :: " + Thread.currentThread().getName()+" thread" );
	}
}

class Case4 extends Thread{
	public void run() {
		System.out.println("Case4 no argument run() method. " + Thread.currentThread().getName());
	}
	public void run(int a) { // overload run method 
		System.out.println("Case4 int argument run() method. " + Thread.currentThread().getName());
	}
}

class Case5 extends Thread{ // If you don’t override run(), thread does nothing
      Case5(){ // In this case, a new thread is created by start(), but since run() is not overridden, 
//    	          the Thread class’s empty run() executes and the thread terminates immediately, while the
//    	           constructor runs in the main thread.
    	  System.out.println("not overriding run() from Thread class  " + 
    	  Thread.currentThread().getName());
      }
}

class Case6 extends Thread{
	public void run() {
		System.out.println("Case6 Run method ::" + Thread.currentThread().getName());
	}
	public void start() {  // your custom implementation won't start new thread
		System.out.println("Case6 Overrided start method:: " + Thread.currentThread().getName());
	}
}

class Case7 extends Thread{
	public void run() {
		System.out.println("Case7 Run() method run by :: " + Thread.currentThread().getName());
	}
	public void start() {
		super.start(); // create new thread and run run()
		System.out.println("Case7 overrided start() method by : " + Thread.currentThread().getName());
	}
}

class Case9 extends Thread{
	public void run() {
		System.out.println("Case9 thread created....");
	}
}
