/*
	This class implements a page table associated with a thread
*/

import java.util.Arrays;

public class PageTable
{
	/* id of the thread a page table is associated with */
	private int tid;

	/* size of the page table */
	private int size;

	/* frame number array. page number is used to index the array to find the corresponding frame number */
	private int[] pageTableArray;

	/* length of the page table */
	private int length;
	
	
	public PageTable(int id, int s)
	{
		tid = id;
		size = s;
		length = 0;
		pageTableArray = new int[s]; 

	}

	/* return the frame number of the page number (index) */
	public int lookupPageTable(int index)
	{
		return pageTableArray[index];
	}

	/* add an entry to the end of the page table */
	public void appendEntry(int frameNumber)
	{
		pageTableArray[length] = frameNumber;
		length++;
	}

	/* return length of the page table */
	public int getLength()
	{
		return length;
	}

	/* return thread ID */
	public int getThreadID()
	{
		return tid;
	}

	/* print the content of the page table */
	public void printPageTable()
	{
		for(int i=0; i<length; i++)
		{
			System.out.println("Thread " + tid + " Page " + i + " has frame number " + pageTableArray[i]); 
		}
	}
}
