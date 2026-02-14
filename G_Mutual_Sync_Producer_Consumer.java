package multi_Threading;

import java.util.*;

public class G_Mutual_Sync_Producer_Consumer {

	public static void main(String[] args) {
		
/*
 *⏺️ What is Polling, and What are the Problems with it? 
	  The process of testing a condition repeatedly till it becomes true is known as polling. 
	  Polling is usually implemented with the help of loops to check whether a particular condition is
      true or not. If it is true, a certain action is taken. For example, in a classic queuing problem 
      where one thread is producing data, and the other is consuming it.
    
 *⏺️ Problem with Polling:
	 ⭕ This wastes many CPU cycles and makes the implementation inefficient. 
	 ⭕ This slows down the execution, and it keeps on checking the condition repeatedly.
	
 *⏺️ How does Java Multi-Threading Tackle this problem? 
	  To avoid polling, Java uses three methods, namely, wait(), notify(), and notifyAll(). 
	  All these methods belong to the object class, so all classes have them. They must be used within 
	  a synchronized block only.
	
 *⏺️ Producer Consumer problem ::
     producer thread is responsible to produce items to the queue on a consumer thread is responsible to
     consume items from the queue if queue is empty the consumer thread will call wait() method and enter
     into waiting state. 
     After producing items to the queue producer thread is a responsible to call notify() method then
     waiting consumer will get that notification and continue it's execution with updated items.
     
   ⭕ wait(): It tells the calling thread to give up the lock and go to sleep until some other thread 
             enters the same monitor and calls notify().
   ⭕ notify(): It wakes up one single thread called wait() on the same object. It should be noted that
               calling notify() does not give up a lock on a resource.
   ⭕ notifyAll(): It wakes up all the threads called wait() on the same object.	
 * 		
 */
		
//		Comm_shar sh = new Comm_shar();
//		Prod pro = new Prod(sh);
//		Consu con = new Consu(sh);	
//		pro.start();
//		con.start();
		
		
		 System.out.println("Main thread started");
	        
	        // Create and start the producer thread
	        Thread producerThread = new Thread(SecondEx.producer, "Producer");
	        
	        // Create and start the consumer thread
	        Thread consumerThread = new Thread(SecondEx.consumer, "Consumer");
	        
	        producerThread.start();
	        consumerThread.start();
	        
	        System.out.println("Main thread exiting");
	        
		// Here, the output demonstrates the interaction between the producer and consumer. 
// The producer adds items to the queue, and the consumer removes them, if the queue is full the producer
//	  waits and if the queue is empty the consumer waits. This show how two thread communicate with each
//	  other without wasting time using wait() and notifyAll().
	        
	}
}

//*******************1> Example **********************************************************************
/*
class Comm_shar{
	List<Integer> li = new ArrayList<>();
	int num = 1;
	public synchronized void incre() throws InterruptedException {		 
		 if(li.size()==10)
		 {
			 System.out.println("entering the waiting state........");
			 wait();
			 Thread.sleep(10000);
		 }
		 li.add(num);
		 System.out.println("Item added :"+num);
		 notify();
		 num++;		
		}		
	public synchronized void decre() throws InterruptedException {	
		if(li.size() == 0) {
			System.out.println("Waiting for remove numbers.....");
			wait();
		}else {			
			int remo =	li.remove(0);
				System.out.println("removing " + remo);		
			notify();
		}
	}	
}

class Prod extends Thread{	
	Comm_shar co;
	Prod(Comm_shar co){
		this.co = co;
	}	
	public void run() {
		while(true) {
		try {	
			System.out.println("Increment:: ");
			co.incre();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}
class Consu extends Thread{	
	Comm_shar co2;
	Consu(Comm_shar co2){
		this.co2 = co2;
	}	
	public void run() {
		while(true) {
		try {
			Thread.sleep(10000);
			System.out.println("Decrement:: ");
			co2.decre();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}    */

//********************2> Example**********************************************************************
// Now, we are going to understand what producer-consumer problem is. In this one thread add items to a
//  queue and the another thread removes items from the queue. We are going to use wait(), notify() and 
//  nortifyAll() methods to ensure that both threads operate efficiently.

class SecondEx {
	
	// Shared queue used by both producer and consumer
	  private static final Queue<Integer> queue = new LinkedList<>();
	
	// Maximum capacity of the queue
	    private static final int CAPACITY = 10;
	    
	 // Producer task
	     static final Runnable producer = new Runnable() {
	        public void run() {
	            while (true) {
	                synchronized (queue) {
	                    
	                    // Wait if the queue is full
	                    while (queue.size() == CAPACITY) {
	                        try {
	                            System.out.println("Queue is at max capacity");
	                            queue.wait(); // Release the lock and wait
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    // Add item to the queue
	                    queue.add(10);
	                    System.out.println("Added 10 to the queue");
	                    queue.notifyAll(); // Notify all waiting consumers
	                    try {
	                        Thread.sleep(2000); 
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    };

	 // Consumer task
	     static final Runnable consumer = new Runnable() {
	        public void run() {
	            while (true) {
	                synchronized (queue) {
	                    
	                    // Wait if the queue is empty
	                    while (queue.isEmpty()) {
	                        try {
	                            System.out.println("Queue is empty, waiting");
	                            queue.wait(); // Release the lock and wait
	                        } catch (InterruptedException e) {
	                            e.printStackTrace();
	                        }
	                    }
	                    // Remove item from the queue
	                    System.out.println("Removed " + queue.remove() + " from the queue");
	                    queue.notifyAll(); // Notify all waiting producers
	                    try {
	                        Thread.sleep(2000); 
	                    } catch (InterruptedException e) {
	                        e.printStackTrace();
	                    }
	                }
	            }
	        }
	    };
}



