package multi_Threading.concurrency_Utilities_Tool;

public class D_Callable_vs_Future_Interfaces {

	public static void main(String[] args) {
	
/*		*  Callable & Future ::
			   
			   >> in the case of Runnable job thread won't return anything after completing the job.
			   >> if a thread is require to return some result after execution then we should go for callable.
			    
			         Callable interface contains only one method =>>
			                                                         V call() throws Exception;
			                public Object call() throws Exception ->> when you override it               
			       
			   >> if we submit Callable object to executor then after completing the job thread returns an Object of
			         the type Future<?>.
			         
			         i.e. future object can be used to retrieve the result from callable job.
			         		
*/		

	}

}
