package lu.uni.concurrency.simple;

public class ConcurrentThreadsSimple {
	
	public static long value;

	public static void main(String[] args) throws InterruptedException {
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
		for (long i=0 ; i<nrOfLoops ; ++i) {
			ConcurrentThreadsSimple.value++;
		}
	}
}
