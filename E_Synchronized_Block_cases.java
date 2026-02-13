package multi_Threading;

public class E_Synchronized_Block_cases {

	public static void main(String[] args) throws InterruptedException {

/*
 * If a very few lines of code required synchronization then it is not recommended to declare entire method
 * as synchronized we have to enclose those few lines of the code by using synchronized block.
      The main advantage of synchronized block over synchronized method is it reduces waiting time of 
      threads and improves performance of the system/application
 * We can declare synchronized block as follows::
      1> to get a lock of current object:  synchronized(this){   }  
           if a thread got lock of current object then only it is allowed to execute this Area.
           
      2> to get a lock of particular object 'b': synchronized(b){    }
          if a thread got lock of particular object 'b' then only it in allowed to execute this Area.
          
      3> to get class level lock : synchronized (Display.class) {      } 
         if a thread got class level lock of "Display" class, then only it is allowed to execute this Area.
 		
 * Lock concept of applicable for object types and class types but not for primitives, hence we can't 
    pass primitive type as a argument to synchronized block otherwise we will get compileTime error saying
     Unexpected type found int required reference.
 *    
 * 
 */
		
		ResourceShared re = new ResourceShared();
		ResourceShared re2 = new ResourceShared();
		Onee t1 = new Onee(re);
		Twoo t2 = new Twoo(re2);
		t1.start();
		t2.start();
		
	}

}
class ResourceShared{
	int no = 1000;
	public void inc() throws InterruptedException {
	   synchronized(ResourceShared.class) { // class level lock
		for(int i=0;i<10;i++) {
		  no++;
		  System.out.println("No:: " + no);
		}
	}
}
	
}
class Onee extends Thread{
	ResourceShared d;
	Onee(ResourceShared d){
		this.d = d;
	}
	public void run() {
			 try {
				d.inc();
				Thread.sleep(1000);
			 } catch (InterruptedException e) {
				e.printStackTrace();
			 }
	}
}
 class Twoo extends Thread{
	 ResourceShared d;
	 Twoo(ResourceShared d){
		 this.d = d;
	 }
	 public void run() {
		
			 try {
				d.inc();
				Thread.sleep(500);
			 } catch (InterruptedException e) {
				e.printStackTrace();
			 }
	 }
 }


