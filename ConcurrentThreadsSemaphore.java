package lu.uni.concurrency.semaphore;

import java.util.concurrent.Semaphore;

public class ConcurrentThreadsSemaphore {
	
	public static long value;
	public static Semaphore sem;

	public static void main(String[] args) throws InterruptedException {
		sem = new Semaphore(1);
		Thread t1 = new IncrementThread(100000);
		Thread t2 = new IncrementThread(100000);
		
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		System.out.println("final value = " + value);
	}

}

class IncrementThread extends Thread {
	
	private long nrOfLoops;
	
	public IncrementThread(long nrOfLoops) {
		this.nrOfLoops = nrOfLoops;
	}
	
	public void run() {
		try {
			for (long i=0 ; i<nrOfLoops ; ++i) {
				ConcurrentThreadsSemaphore.sem.acquire();
				ConcurrentThreadsSemaphore.value++;
				ConcurrentThreadsSemaphore.sem.release();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
