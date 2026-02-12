package multi_Threading;

public class D_Synch_RaceCondition_problem {

	public static void main(String[] args) throws InterruptedException {
	
	/*
	 * Synchronized is the modifier applicable only for methods and blocks but not for classes and variable
	 *   ⭕ if multiple threads or trying to operate simultaneously and same java object then there may be 
	 *      chance of data inconsistency problem, to overcome this problem we should go for synchronized keywords.
	 *   ⭕ if a method or block declared as synchronized then at a time only one thread is allowed to execute
	 *       that method or block on the given object so that data inconsistency problem will be resolved.
	 *       
	 * ⚠️  Why is Synchronization Needed?   =>> Prevents Data Inconsistency, Avoids Race Conditions,
	 * 										Maintains Thread Safety, Ensures Data Integrity. 
	 *       
	 *  The main advantage of synchronized keyword is we can resolve data inconsistency problems but,
	 *    a main disadvantage of synchronized keyword is it increases waiting time of threads and creates performance
	 *     problem hence if there is no specific requirement then it is not recommended to use synchronized keyword.
	 * 
	 * ⭕ Internally synchronization concept is implement by using lock, every object in java has a unique lock.
	 * ⭕ whenever we are using synchronized keyword then only lock concept will come into the picture.
	 *    
	 *   ⭕ if a thread wants to execute synchronized method on the given object, first it has to get lock of
	 *     that object,
      			 once thread got the lock then it is allowed to execute any synchronized method on that object.
      		     once method execution completes automatically thread release a lock.
	 * ⭕ Acquiring and releasing lock internally take care by JVM and programmer not responsible for this activity.
	 *          
	 * ⭕ while a thread executing synchronized method on the given object that remaining threads not allowed
	 *   to execute any synchronized method simultaneously on the same object but remaining threads are allowed 
	 *   to execute non synchronized method simultaneously.
	 *  
	 * ⭕ Lock concept is implemented based on object but not based on method.
	 *  
	 *  
	 * 
	 * 
	 * ⭕ ⚠ Race Condition = can not predict the output
	 *    whenever shared resources in java & multiple more than one thread accessing, reading and writing
	 *     same resource there is some inconsistency in behavior in the resource it behaves unexpectedly
	 *     & randomly it is called as rest condition.	
	 *      
	 *   To avoid race condition use synchronization technique.  
	 */
		
		One_Source sourc = new One_Source();  // get one shared resource
		
		User1 one = new User1();
		User2 two = new User2();
		one.dat = sourc;
		two.dat2 = sourc;
		one.start();
		two.start();	
		Thread.sleep(1000);
		System.out.println(sourc.count);
//  ***************************************************************************************************
		
		Displaydat d = new Displaydat();
		GetThreadCreat t1 = new GetThreadCreat(d,"John");
		GetThreadCreat t2 = new GetThreadCreat(d,"Catlyn");
		GetThreadCreat t3 = new GetThreadCreat(d,"Lucy");
		GetThreadCreat t4 = new GetThreadCreat(d,"Rose");
		
		t1.start();  t2.start();  t3.start();  t4.start();
		
		
	}

}

class One_Source {
	int count = 0;
	public void increment() {
		count++;
	}
}
class User1 extends Thread{
	One_Source dat;
	public void run() {
		for(int i=0; i<10000;i++) {
			dat.increment();
		}
	}	
}
class User2 extends Thread{
	One_Source dat2;	
	public void run() {
		for(int i=0;i<10000;i++) {
			dat2.increment();
		}
	}
}
// *****************************************

class Displaydat{
/*
 * if we are not declaring wish() synchronized then both threads will be executed simultaneously and hence
 *    we will get irregular output :: mixed output, not consistent
 * if we declare wish() as synchronized then at a time only one thread is allowed to execute wish() and 
 *     on the given display object hence, we will get regular output ::  Good Morning:: t1 10 time , 
 *     																	Good Morning:: t2 10 time, so on	
 */
	
//	public void wish(String name) {   // run without synch first 
		public synchronized void wish(String name) { // then run with synch keyword
		for(int i=0;i<10;i++	) {
			System.out.print("Good Morining:: ");
			try {
				Thread.sleep(1000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			System.out.println(name);
		}
	}
}
class GetThreadCreat extends Thread{
	Displaydat t;
	String name;
	GetThreadCreat(Displaydat t,String name){
		this.t = t;
		this.name = name;
	}
	
	public void run() {
		t.wish(name);
	}
	
}

