package multi_Threading.concurrency_Utilities_Tool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class D_Callable_vs__Runnable_Interface {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

/*
 * If a thread is not required to return anything after completing the job then we should go for Runnable.
 	>> Runnable interface contains only one method is run().
 	>> Runnable job is not required to anything and hence return type of run() is void.
 	>> Within the run() method if there is any chance of rising any checkedException compulsory we should
    				handle by using try catch because we can't use throws keyword for run() method.
    	>> Runnable interface present in java.lang package.		
    	>> Introduced in 1.0 version.	
 	 
 *  If a thread required to return something after completing the job then we should go for Callable interface.
   	 >> Collable interface contains only one method is call().
     >> Collable job is required to return something and hence return type of call() method is Object.
     >> Within call() method if there is any chance of rising checkedException we not required to handle 
   				by using try catch because call() method already throws Exception.
     >> Callable interface present in java.util.concurrent package.
     >> Introduced in 1.5 version.				
 
 * 		
 */
		
		MyCallable[] cal = {
				new MyCallable(10),
				new MyCallable(20),
				new MyCallable(30),
				new MyCallable(40),
				new MyCallable(50)
		};
		
		ExecutorService ex = Executors.newFixedThreadPool(3);
		for(MyCallable call : cal) {
			  Future<Integer> f =  ex.submit(call);
			  System.out.println(f.get());
		}
		ex.shutdown();
		System.out.println("________________________________________________________");
// *************************************************************************************
		MyRunnable[] runn = {
			new MyRunnable("Cooking"),
			new MyRunnable("Printing"),
			new MyRunnable("Washing"),
			new MyRunnable("Watching TV"),
		};
		
		ExecutorService ex2 = Executors.newFixedThreadPool(2);
		for(MyRunnable r:runn) {
			Future<?> f = ex2.submit(r,"Done");
			System.out.println(f.get());
		}
		ex2.shutdown();
	}
}
class MyCallable implements Callable<Integer> {
	int a;	
	MyCallable(int a){
		this.a = a;
	}
	@Override
	public Integer call() throws Exception {
		
		System.out.println(Thread.currentThread().getName() + " is.... responsible to find sum of "
				+ "first " + a + " numbers.");
		int sum = 0;
		for(int i =0;i<a;i++) {
			sum = sum + i;
		}
		return sum;
	}
}
// **************************************************************************************************
class MyRunnable implements Runnable{
	
	String name;
	MyRunnable(String name){
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