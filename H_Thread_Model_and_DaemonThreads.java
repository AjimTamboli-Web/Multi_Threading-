package multi_Threading;

public class H_Thread_Model_and_DaemonThreads {

	public static void main(String[] args) {
	
/* Daemon Thread ==>>> 
 * The threads which are executing in the background are called daemon threads 
    ex:: garbage collector, signal dispatcher, Attach listener etc.
 
 ⭕  The main objective of daemon threads is to provide support for non-daemon threads(main thread).
   Ex: if a main threads runs with a low memory then JVM runs garbage collector to destroy useless
   objects, so that number of bytes of free memory will be improved, with this free memory main 
   thread can continue its execution.
  
 ⭕ Usually daemon threads having low priority but, based on our requirement daemon threads can run
   with a high priority also.
   
 ⭕ we can check daemon nature of a thread by using isDaemon() of Thread class 
    ->> public boolean isDaemon()      
 ⭕ we can change daemon nature of a thread by using setDaemon() of Thread class        
    ->> public void setDaemon(boolean b) 
     
    but, changing daemon nature is possible before staring of a thread only, after starting a thread
    if we are trying to change daemon nature then we will get RE:: IllegalThreadStateException .
    
 * ⏺️ Default nature of thread:: 
      by default main thread is always non-daemon and for all remaining threads the daemon nature
      will be inherited from parent to child that is if the parent thread is daemon then 
      automatically child threads is also daemon,
      and if the parent thread is non-daemon then automatically child thread is also non-daemon.
     
  Note:: it is impossible to change daemon nature of main thread because it is already 
        started at JVM at a beginning. 
    
  >> Whenever last non-daemon thread terminate automatically all daemon threads will be terminated,
      irrespective of there position.
    
 * If we are commenting line no76 both main and child threads are non-demon, and hence both thread
    will be executed until there completion. 
 * If we are not commenting line no76 then main thread is non-daemon and child thread is daemon.
    hence, whenever main thread terminates automatically child thread will be terminated in this 
     case output is :: End of Main thread |   End of Main thread  |  child thread
                       child thread       |                       | End of Main thread
  
  *************************************************************************************************
    
 * Java multi-threading concept is implemented by using the following two models ::
   ⏺️ 1> green thread model ->>
           >> The thread which is managed completely by JVM without taking underlying OS support,
                is called green thread.
           >> very few operating system like SUN Solaries provide support for green thread model.
           >> Anyway green thread model is deprecated and not recommended to use.
           
   ⏺️ 2> native OS of model -->>
          >> The thread which is managed by the JVM with the help of underlying OS, is called 
               native OS model.
          >> All windows based operating system provide support for native OS model.
   *********************************************************************************************
 *   ⚠️ How to stop a thread ==>
          >> we can stop a thread execution by using stop() of Thread class,
                	public void stop();
          >> if we call stop() then immediately thread will enter into dead state,
            	   anyway stop() is deprecated and not recommended to use.
            	   
 *   ⚠️ How to suspend and resume of a thread ==>>    
          >> we can suspend a thread by using suspend() of Thread class,then immediately the thread
                  will be enter into suspended state.
          >> we can resume a suspended thread by using resume() of Thread class then suspended
                  thread can continue it's execution. 
            public void suspend()
            public void resume()  =>>anyway these methods are deprecated and not recommended to use.	
 * 
 */
           DemoDemon de = new DemoDemon();
           de.setDaemon(true);
           de.start();
           System.out.println("End of Main thread....");
	   
           
//           Thread.currentThread().setDaemon(true);  // RE:: IllegalThreadStateException
           // because main thread started from JVM at a beginning.
           
           System.out.println("is child thread is daemon thread:: " + de.isDaemon());   // true
	}

}
class DemoDemon extends Thread{
	
	public void run() {
		for(int i =0;i<10;i++) {
			System.out.println("Child thread");
			try {
				Thread.sleep(1000);
			}catch(InterruptedException er) {
				
			}
		}
	}
}