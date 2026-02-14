package multi_Threading;

import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * volatile Keyword in Java :: >>
 * Using volatile is yet another way (like synchronized, atomic wrapper) of making class thread-safe.
     Thread-safe means that a method or class instance can be used by multiple threads at the same time 
     without any problem.  Consider the below example... 
 
 *    class SharedObj
	 {
        // Changes made to sharedVar in one thread may not immediately reflect in other thread
        
        static int sharedVar = 6;
      }
      
 *   If one thread modifies its value the change might not reflect in the original one in the main memory
      instantly. This depends on the write policy of cache. Now the other thread is not aware of the
      modified value which leads to data inconsistency.
      
      		
 *  Note that ::  writing of normal variables without any synchronization actions, might not be visible
                   to any reading thread (this behavior is called sequential consistency). 
  
 *    class SharedObj
     {
       // volatile keyword here makes sure that the changes made in one thread are 
       // immediately reflect in other thread
       
       static volatile int sharedVar = 6;
     }  
     
 *  Note that :: volatile should not be confused with the static modifier. static variables are class 
            members that are shared among all objects. There is only one copy of them in the main memory.
             
             
 */
public class C_Volatile_keyword {

    // static used here because a non-static variable cannot be referenced from a static context
	private static final Logger LOGGER 
	 =   Logger.getLogger(C_Volatile_keyword.class.getName());	
    private static volatile int MY_INT = 0;

// exit variable to stop both the main and inside threads  (2nd example)
//    static boolean exit = false;        // o/p infinite loop
    
    static volatile boolean exit = false; 
    // above shows that when we use a volatile boolean flag, we do not run into infinite loops. 
    //This is because the volatile variable directly stored in the main memory. In other words, changes
    //made by one thread are visible to other threads. Thus using volatile makes our code, thread safe.
    
   public static void main(String[] args) {
		    
		        new ChangeListener().start();
		        new ChangeMaker().start();
		        
//************************************************************************************
		        // 2nd example::
		        System.out.println("started main thread..");

		        // a thread inside main thread
		        new Thread() {
		            public void run()
		            {
		                System.out.println("started inside thread..");

		        // inside thread caches the value of exit,so changes made to exit are not visible here
		                while (!exit) // will run infinitely
		                {
		                }

		                // this will not be printed.
		                System.out.println("exiting inside thread..");
		            }
		        }.start();

		        try {
		            Thread.sleep(500);
		        }
		        catch (InterruptedException e) {
		            System.out.println("Caught :" + e);
		        }

		        // so that we can stop the threads
		        exit = true;
		        System.out.println("exiting main thread.."); 
		        
 }

   
   static class ChangeListener extends Thread {
		        @Override
		        public void run()
		        {
		            int local_value = MY_INT;
		            while (local_value < 5) {
		                if (local_value != MY_INT) {
		                    LOGGER.log(
		                        Level.INFO,
		                        "Got Change for MY_INT : {0}",
		                        MY_INT);
		                    local_value = MY_INT;
		                }
		            }
		        }
		    }
		  
		    static class ChangeMaker extends Thread {
		        @Override public void run()
		        {
		            int local_value = MY_INT;
		            while (MY_INT < 5) {
		                LOGGER.log(Level.INFO,
		                           "Incrementing MY_INT to {0}",
		                           local_value + 1);
		                MY_INT = ++local_value;
		                try {
		                    Thread.sleep(500);
		                }
		                catch (InterruptedException e) {
		                    e.printStackTrace();
		                }
		            }
		        }
		    }
		}
/*
 * 
 *  Output (With the Volatile Keyword):

		Incrementing MY_INT to 1
		Got Change for MY_INT : 1
		Incrementing MY_INT to 2
		Got Change for MY_INT : 2
		Incrementing MY_INT to 3
		Got Change for MY_INT : 3
		Incrementing MY_INT to 4
		Got Change for MY_INT : 4
		Incrementing MY_INT to 5
		Got Change for MY_INT : 5
		
 *   Output (Without the Volatile Keyword)

		Incrementing MY_INT to 1
		Incrementing MY_INT to 2
		Incrementing MY_INT to 3
		Incrementing MY_INT to 4
		Incrementing MY_INT to 5	
				
*/