import java.util.Scanner;
/**
 * Factory class to start five threads.  
 *
 */
public class Factory
{
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		
		System.out.println("Please enter a Memory size: ");
		int memorySize = in.nextInt();
		
		System.out.println("Please enter a Frame size: ");
		int frameSize = in.nextInt();

		int numberOfFrames = (Integer) memorySize/frameSize;
		
		PhysicalMemory physicalMemory = new PhysicalMemory(numberOfFrames);
		Thread[] threadArray = new Thread[5];

		for(int i=0; i<5 ;i++)
	     		threadArray[i] = new Thread(new MemoryThread(physicalMemory, i));
		for(int i=0; i<5; i++)
			threadArray[i].start();
	}
}
