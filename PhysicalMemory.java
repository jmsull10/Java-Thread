import java.util.concurrent.Semaphore;

public class PhysicalMemory {
	
	private boolean[] memoryAllocationMap;
	private int freeFrameCounter;
	private PageTableList pL;
	private Semaphore mutex;
	private int frameNum;

	public PhysicalMemory(int numberOfFrames) {
		memoryAllocationMap = new boolean[numberOfFrames];
		freeFrameCounter = numberOfFrames;
		pL = new PageTableList();
		mutex = new Semaphore(1);
		frameNum = 0;
	}
	
	public boolean requestMemory(int id, int requestSize) {
		try { // tries to acquire mutex, if it cant, catches the exception.
			mutex.acquire();
		} catch (InterruptedException e) {
		}
		
		if (requestSize > freeFrameCounter) { // checks to see if there are enough open frames for the requested size of thread. If it is not, mutex is released and returns false.
			mutex.release();
			return false;
		}
		System.out.println("Thead " + id + " has entered requestMemory");
		PageTable pT = new PageTable(id, requestSize);

		int count = 0;
		while(requestSize > 0) {// while requestSize is greater than zero, it will continuously append values, until it is zero.
			if (!memoryAllocationMap[count]) { // if the index in memoryAllocationMap is false, it will append entries to the pageTable, decrease the frame count and the requested size.
				pT.appendEntry(frameNum);
				frameNum++;
				requestSize--;
				freeFrameCounter--;
			}
			count++;
		}

		System.out.println("Thread " + id + " requested a size of " + count + " , the number of available frames left is " + freeFrameCounter);
		pL.addFirst(pT);
		
		System.out.println("Thread " + id + " has left requestMemory.");
		System.out.println("Memory space was allocated for thread " + id + " and a page table was created.");

		mutex.release();
		return true;
	}
	
	public int accessMemory(int id, int pageNumber) {
		PageTable pT = pL.lookup(id);
		if (pT == null) { // checks to see if the pageTable is null, if it is, it returns -1.
			return -1;
		}
		pT.printPageTable();
		int frameNumber = pT.lookupPageTable(pageNumber);
		return frameNumber;
	}
	
	public void releaseMemory(int id) {
		try { // tries to acquire mutex, it if cant, catches the exception.
			mutex.acquire();
		} catch (InterruptedException e) {
		}	
		PageTable pT = pL.lookup(id);
		
		for(int i = 0; i < pT.getLength(); i++) { // for index in length of pageTable, it will get the indexes of the ID and set all of its values to false. Increasing the number of free frames.
			memoryAllocationMap[pT.lookupPageTable(i)] = false;
			frameNum--;
			freeFrameCounter++;
		}
		
		System.out.println("Thread " + id + " has released its memory.");
		mutex.release();
	}
}
