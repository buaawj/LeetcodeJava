import java.util.*;

public class LinkedLists {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Linked List");
	}
	
	public static class ListNode
	{
		int val;
		ListNode next;
		public ListNode(int val)
		{
			this.val = val;
			next = null;
		}
	}
	
	// Leetcode 19 Remove Nth Node From End of List
		public static ListNode removeNthFromEnd(ListNode head, int n) {
			if(head==null )
				return null;
			ListNode dummy = new ListNode(0);
			dummy.next = head;
	        ListNode cur = dummy;
	        for(int i=0; i<n; ++i)
	        {
	        	if(cur != null)
	        		cur = cur.next;
	        	else
	        		return null;
	        }
	        
	        ListNode  cur1 = dummy;
	        while(cur.next != null)
	        {
	        	cur1 = cur1.next;
	        	cur = cur.next;
	        }

	        cur1.next = cur1.next.next;
	        
	        return dummy.next;
	        
	    }
	    
		public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		
			ListNode dummy = new ListNode(0);
			ListNode cur = dummy;
			while(l1!=null && l2!=null)
			{
				if(l1.val > l2.val)
				{
					cur.next = l2;
					l2 = l2.next;
				}
				else
				{
					cur.next = l1;
					l1 = l1.next;
				}
				cur = cur.next;
			}
			
			if(l1 != null)
			{
				cur.next = l1;
			}
			
			if(l2 != null)
			{
				cur.next = l2;
			}
			
			return dummy.next;
		}
		
		
		 public ListNode detectCycle(ListNode head) {
		        if(head==null || head.next ==null)
		            return head;
		        
		        ListNode slow = head, fast = head;
		        
		        while(fast!=null && fast.next !=null)
		        {
		        	slow = slow.next;
		        	fast = fast.next.next;
		        	if(slow ==fast)
		        		break;
		        }
		        
		        if(slow != fast)
		        	return null;
		       
		        slow = head;
		        while(slow != fast)
		        {
		        	slow = slow.next;
		        	fast = fast.next;
		        }
		        return slow;
		        
		        
		    }
		    
		    public static void reorderList(ListNode head) 
		    {
		    	if(head ==null || head.next ==null)
		    		return;
		    
		    	ListNode slow = head, fast = head.next;
		    	while(fast != null && fast.next !=null)
		    	{
		    		slow = slow.next;
		    		fast = fast.next.next;
		    	}
		    	
		    	ListNode head1 = slow;
		    	ListNode cur = head1.next;
		    	while(cur!=null && cur.next != null)
		    	{
		    		ListNode move = cur.next;
		    		cur.next = move.next;
		    		move.next = head1.next;
		    		head1.next = move;
		    	}
		    	
		    	for(cur=head; cur!=head1 && head1.next!=null; cur=cur.next.next)
		    	{
		    		ListNode move = head1.next;
		    		head1.next = move.next;
		    		move.next = cur.next;
		    		cur.next = move;
		    	}
		    }
	
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
        if(l1==null && l2!=null)
        	return null;
        
        ListNode dummy = new ListNode(0);
        ListNode l3 = dummy;
        int carry = 0;
        while(l1!=null || l2!=null || carry !=0)
        {
        	int sum = carry;
        	if(l1!=null)
        	{
        		sum += l1.val;
        		l1 = l1.next;
        	}
        	
        	if(l2 != null)
        	{
        		sum += l2.val;
        		l2 = l2.next;
        	}
        	
        	l3.next = new ListNode(sum%10);
        	l3 = l3.next;
        	carry = sum/10;
        }
        
        return dummy.next;
        
    }
	
}
