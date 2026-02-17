package multi_Threading;

public class J_Group_Thread {

	public static void main(String[] args) throws InterruptedException {

/*
 * ThreadGroup==>
 
   Based on functionality we can group threads into a single unit, which is nothing but ThreadGroup.
    that is thread group contains a group of threads .
    in addition two threads, ThreadGroup can also contain sub-ThreadGroups.
 * The main advantage of maintaining threads in the form of ThreadGroup is we can perform common 
     operations very easily.
 * Every thread in java belongs to some group, main thread belongs to mainGroup.
 * Every ThreadGroup in java is the childGroup of system group either directly or indirectly, hence
    system group access root for all ThreadGroups in java.
 *  System groups contains several system level threads like Finalizer, Reference Handler,
    Signal Dispatcher, Attach Listener etc....
    
 >>  ThreadGroup is a java class present java.lang package and it is the direct child class of Object.
  
 * ⏺️ Constructors::> 
      
  1> ThreadGroup g = new ThreadGroup(String name); Creates a new threadGroup with the specified groupName
      >> The parent of this new group is the ThreadGroup of currently executing thread.
      
  2> ThreadGroup g2 = new ThreadGroup(ThreadGroup m, String GroupName);
      >> Creates a new threadGroup with a specified groupName, the parent of this new threadGroup is 
         specified parent group.
         Ex. ThreadGroup g2 = new ThreadGroup(g,"second");
   
 * ⏺️ Important methods of ThreadGroup ::>
 
    ⭕ String getName() -           returns name of the ThreadGroup
    ⭕ int getMaxPriority() -       returns max priority of thread group
    ⭕ void setMaxPriority(int p) - to set maximum priority of ThreadGroup,The default max priority is 10.
        	Threads in the ThreadGroup that have already higher priority won't be affected but newly added 
        	threads this maxPriority is applicable.
    ⭕ ThreadGroup getParent() -   returns parent group of current thread 
    ⭕ void list()  -              it prints information about threadGroup to the console.
    ⭕ int activeCount() -         returns a number of active threads present in the threadGroup
    ⭕ int activeGroupCount() -    it returns number of active groups present in the current ThreadGroup
    ⭕ int enumerate(Thread[] t) - To copy all active threads of this ThreadFroup into provided Thread[]
                                     in this case sub-ThreadGroup thread also will consider.
    ⭕ int enumerate(ThreadGroup[] g) - to copy all active sub-ThreadGroups into ThreadGroup[]   
    ⭕ boolean isDaemon() -       to check wether the threadGroup is daemon or not
    ⭕ void setDaemon(boolean b) 
    ⭕ void interrupt() -         to interrupt allwaiting or sleeping threads present in the threadGroup
    ⭕ void destroy() -              to destroy threadGroup and it's a sub-ThreadGroup
 *     
 */
		
		
		ThreadGroup g = new ThreadGroup("First Group");
		System.out.println(g.getParent().getName());
		ThreadGroup g1 = new ThreadGroup(g,"Second group");
		System.out.println(g1.getParent().getName());
		
		Thread t1 = new Thread(g1,"Thread 1");
		Thread t2 = new Thread(g1,"Thread 2");
		
		g1.setMaxPriority(3);
		
		Thread t3 = new Thread(g1,"Thread 3");
		
		System.out.println(t1.getPriority());  // 5
		System.out.println(t2.getPriority());  // 5
		System.out.println(t3.getPriority());  // 3
// *********************************************************
		
		ThreadGroup pg = new ThreadGroup("Parent Group");
		ThreadGroup cg = new ThreadGroup(pg,"Child Group");
		
		
		MyThread313 myt = new MyThread313(pg,"child thread");
		MyThread313 myt2 = new MyThread313(pg,"child thread 2");
		myt.start();
		myt2.start();
		
		System.out.println("pg activeCount::  " + pg.activeCount());
		System.out.println("pg activeGroupCount::  " + pg.activeGroupCount());
		pg.list();
		
		Thread.sleep(10000);
		System.out.println("pg activeCount::  " +pg.activeCount());
		System.out.println("pg activeGroupCount::  " +pg.activeGroupCount());
		pg.list();
//*******************************************************************
		System.out.println("_________________________________________________________");
		
//write a program to print all active threadNames belongs to systemGroup and it's a childGroups 
		System.out.println("SystemGroup threads names and it is deamon??");
		
		ThreadGroup syste = Thread.currentThread().getThreadGroup().getParent();
		Thread[] tharr = new Thread[syste.activeCount()];
		syste.enumerate(tharr);
		
		for(Thread t:tharr) {
			System.out.println("Thread " + t.getName() + " ::>> " + "isDaemon: " + t.isDaemon());
		}
		
	}

}
class MyThread313 extends Thread{
	
	MyThread313(ThreadGroup g, String name){
		super(g,name);
	}
	public void run() {
		System.out.println("Child Thread");
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			
		}
	}
}