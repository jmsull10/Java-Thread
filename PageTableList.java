/* This class implements a linked list which contains all the page tables in the system */

public class PageTableList
{
	public PageTableList()
	{
		first = null;
	}

	/* add a new entry to the head of the linked list */
	public void addFirst(PageTable pt)
	{
		Node newNode = new Node();
		newNode.pt = pt;
		newNode.next = first;
		first = newNode;
	}

	/* return the page table associated with the thread id */
	public PageTable lookup(int id)
	{
		Node t = first;
		while(t != null)
		{	
			if(t.pt.getThreadID() == id)
				return t.pt;
			t = t.next;
		}

		return null;
	}

	/* head of the linked list */
	private Node first;

	private class Node
	{	
		public PageTable pt;
		public Node next;
	}	


}