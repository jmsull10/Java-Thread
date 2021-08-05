/**
 * Factory class to start five threads.  
 *
 */
public class Factory
{
	public static void main(String args[]) {
		PhysicalMemory physicalMemory = new PhysicalMemory();
		Thread[] threadArray = new Thread[5];

		for(int i=0; i<5 ;i++)
	     		threadArray[i] = new Thread(new MemoryThread(physicalMemory, i));
		for(int i=0; i<5; i++)
			threadArray[i].start();
	}
}
