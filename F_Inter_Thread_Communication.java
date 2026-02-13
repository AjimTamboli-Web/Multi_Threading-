package multi_Threading;

public class F_Inter_Thread_Communication {

	public static void main(String[] args) throws InterruptedException {
	
/*
 *⭕ Two threads can communicate with each other by using wait(), notify() and notifyAll() methods.
    The thread which is expecting updation is responsible to call wait() method then immediately the 
      thread will enter into waiting state. 
  * The thread which is responsible to perform updation, after performing updation it is responsible 
      to call notify() method then waiting thread will get that notification and continue it's execution 
       with those updated items.
    
  ⭕ wait() notify() and notifyAll() all methods present in Object class but not in thread class because 
     thread can call these methods on any java object. 
   
  ⭕ To call wait() notify() or notifyAll() methods on any object, thread should be owner of that object.
  
     i.e. thread should has lock of that object, that is thread should be inside synchronized area.
   
     hence, we can call wait(), notify() and notifyAll() methods only from synchronized area otherwise 
        we will get runtimeException saying IllegalMonitorStateException.
   
  ⭕  if a thread calls wait() method on any object it immediately release the lock of particular object
       and enter into waiting state.
   
  ⭕  if a thread calls notify() on any object it release the lock of that object may not immediately
       except wait() notify() and notifyALl() there is no other method where thread releases the lock.
   
    ⚠️  which of the following is valid >> 
     
  1> if a thread calls wait() immediately it will enter into waiting state without releasing any lock.
     --> invalid
  2> if a thread calls wait() method it releases the lock of that object but may not immediately. 
     ---> invalid
  3> if thread calls wait() on any object it releases all locks acquired by that thread and immediately
       enter into waiting state.
     ----> invalid
  4> if thread calls wait() method on any object it immediately releases the lock of that particular
       object and enter into waiting state.
     ----> valid ✔️
  5> if thread calls notify() on any object it immediately releases the lock of that particular object.
     ----> invalid
  6> if thread calls notify() on any object it releases the lock of that object but may not immediately.
    ----> valid  ✔️
   
   ⏺️ Overloads::>>
      public final void wait()               throws InterruptedException
      public final native void wait(long ml) throws InterruptedException
      public final void wait(long ml,int ns) throws InterruptedException
   
      public final native void notify()
      public final native void notifyAll()
   
  Note:: Every wait() method throws InterruptedException which is checked exception, hence whenever we 
         are using wait() compulsory we should handle this interrupted exception either by try catch or
         throws keyword otherwise we will get CompileTimeError.
 * 		
 */

		My_thread b = new My_thread();
		b.start();
//		Thread.sleep(100);
		synchronized(b) {
			System.out.println("Main thread calling wait() method:: ");
			b.wait(1000);   // waiting state
			System.out.println("Main thread got notification");
			System.out.println(b.total);
		}
	}

}
class My_thread extends Thread{
	int total = 0;
	public void run() {
		synchronized(this) {
			System.out.println("Child thread start calculation ");
		for(int i=0;i<=100;i++) {
			total+=i;
		}
		System.out.println("Child thread giving notification");
		this.notify();  // notify thread
	}
  }
}