/**
 * This is the thread to simulate the request, access and release of memory space
 */


import java.util.Random;

public class MemoryThread implements Runnable
{
   public MemoryThread(PhysicalMemory m, int id) {
      physicalMemory = m;
      threadID = id;
   }
   
   public void run()
   {
	//Generate a memory request of a random size
	Random generator = new Random();
	int requestSize = 1 + generator.nextInt(MAX_REQUEST_SIZE);
	//Debuging message
	//System.out.println("Thread " + threadID + " has been started");

	//request memory space
	boolean flag = physicalMemory.requestMemory(threadID, requestSize);

	//Debuging message
	//System.out.println("Thread " + threadID + " has been allocate memory space " + flag);

	while (!flag)
	{
		flag = physicalMemory.requestMemory(threadID, requestSize);
	}

	//Generate a random memory access
	int requestPageNumber =	generator.nextInt(requestSize);
	int frameNumber = physicalMemory.accessMemory(threadID, requestPageNumber);
	System.out.printf("Thread %d requests for page number %d which has frame number %d \n", threadID, requestPageNumber, frameNumber);
	
	// Release the memory
	physicalMemory.releaseMemory(threadID);
   }
   
   private PhysicalMemory physicalMemory;
   private int threadID;
   public static final int MAX_REQUEST_SIZE = 50;
}
