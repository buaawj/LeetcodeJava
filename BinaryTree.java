import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IllegalFormatCodePointException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.plaf.basic.BasicBorders.MarginBorder;
import javax.swing.text.AbstractDocument.DefaultDocumentEvent;
import javax.swing.tree.TreeNode;



public class BinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println(maxSurpasser(new int[]{10, 3, 7, 1, 23, 14, 6, 9}));

	}
	    
    public static int trap(int[] height) 
    {
    	int n = height.length;
    	int[] left = new int[n];
    	int[] right = new int[n];
    	
    	left[0] = height[0];
    	for(int i=1; i<n; ++i)
    	{
    		left[i] = Math.max(height[i], left[i-1]); 
    	}
    	
    	right[n-1] = height[n-1];
    	for(int i=n-2; i>=0; --i)
    	{
    		right[i] = Math.max(height[i], right[i+1]); 
    	}
    	
    	int ret = 0;
    	for(int i=1; i<n-1; ++i)
    	{
    		ret += Math.min(left[i], right[i]) - height[i];
    	}
    	
    	return ret;
    	
    }
	
	
    public static int numDistinct(String s, String t) 
    {
    	int m = s.length(), n = t.length();
    	int[][] dp = new int[m+1][n+1];
    	
    	dp[0][0] = 1;
    	for(int i=1; i<=m; ++i)
    		dp[i][0] = 1;
    	
    	for(int i=1; i<=m; ++i)
    	{
        	for(int j=1; j<=n; ++j)
        	{
        		dp[i][j] = dp[i][j-1];
        		if(s.charAt(i-1) == t.charAt(j-1))
        			dp[i][j] += dp[i-1][j-1];
        	}
    	}
    	
    	return dp[m][n];
    }
	
    public List<List<String>> partition(String s) 
    {
    	List<List<String>> ret = new ArrayList<>();
    	List<String> path = new ArrayList<>();
    	int n = s.length();
    	boolean[][] dp = new boolean[n][n];
    	for(int i=n-1; i>=0; --i)
    	{
    		for(int j=i; j<n; ++j)
    		{
    			if(dp[i+1][j-1] && ( j-i<2 ||s.charAt(i) == s.charAt(j) ))
    				dp[i][j] = true;
    		}
    	}
    	dfs(ret, path, dp, s, 0);
    	return ret;
    }
    
    public void dfs(List<List<String>> ret, List<String> path, boolean[][] dp, String s, int start)
    {
    	if(start == s.length())
    	{
    		ret.add(new ArrayList<String>(path));
    		return;
    	}
    	
    	for(int i=start; i<s.length(); ++i)
    	{
    		if(dp[start][i]==false)
    			continue;
    		path.add(new String(s.substring(start, i+1)));
    		dfs(ret, path, dp, s, i+1);
    		path.remove(path.size()-1);
    	}
    }
	
    public int minCut(String s)
    {
    	int n = s.length();
    	boolean[][] dp = new boolean[n][n];
    	int[] cut = new int[n+1];
    	cut[n] = -1;
    	for(int i=n-1; i>=0; --i)
    	{
    		cut[i] = n-i-1;
    		for(int j=n-1; j>=i; --j)
    		{
    			if(s.charAt(i) == s.charAt(j) && (j-i<2 || dp[i+1][j-1] ))
    			{
    				dp[i][j] = true;
    				cut[i] = Math.min(cut[i], 1+cut[j+1]);
    			}
    		}
    	}
    	
    	return cut[0];
    }
    
    public class UndirectedGraphNode
    {
    	int label;
    	List<UndirectedGraphNode> neighbors;
    	public UndirectedGraphNode(int label)
    	{
    		this.label = label;
    		neighbors = new ArrayList<UndirectedGraphNode>();
    	}
    }
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        
        map.put(node, new UndirectedGraphNode(node.label));
        queue.add(map.get(node));
        
        while(!queue.isEmpty())
        {
        	UndirectedGraphNode oldNode = queue.poll();
        	for(UndirectedGraphNode oldneighbor: oldNode.neighbors)
        	{
        		if(map.containsKey(oldneighbor) == false)
        		{
        			map.put(oldneighbor, new UndirectedGraphNode(oldneighbor.label));
        			queue.add(map.get(oldneighbor));
        		}
        		map.get(oldNode).neighbors.add(map.get(oldneighbor));
        	}
        }
       
        return map.get(node);
    }
    
    public  class RandomListNode {
    	      int label;
    	      RandomListNode next, random;
    	      RandomListNode(int x) { this.label = x; }
    	  };
    	 
    
    public RandomListNode copyRandomList(RandomListNode head)
    {
    	
    	if(head == null )
    		return null;
    	
    	for(RandomListNode cur = head; cur !=null; cur=cur.next.next)
    	{
    		RandomListNode newNode = new RandomListNode(cur.label);
    		newNode.next = cur.next;
    		cur.next = newNode;
    	}
    	
    	for(RandomListNode cur = head; cur !=null; cur=cur.next.next)
    	{
    		if(cur.random != null)
    			cur.next.random = cur.random.next;
    	}
    	
    	RandomListNode dummy = new RandomListNode(0);    	
    	RandomListNode cur1 = dummy;
    	
    	for(RandomListNode cur = head; cur!= null; cur = cur.next)
    	{
    		cur1.next = cur.next;
    		cur1 = cur1.next;
    		cur.next = cur1.next;
    	}
    	return dummy.next;
    }

   

    public boolean isOneEditDistance(String s, String t) 
    {
    	int m = s.length(), n = t.length();
    	if(m > n)
    		return isOneEditDistance(t, s);
    	if(n-m > 1)
    		return false;
    	int i = 0;
    	int diff = n- m;

    	while(i<m && s.charAt(i)==t.charAt(i)) 
    	{
    		++i;
    	}
    	if(i==m)
    		return diff > 0;
    	if(diff ==0) ++i;
    	while(i<m && s.charAt(i)==s.charAt(i+diff))
    	{
    		++i;
    	}
    	
    	return i==m;
    }
    
    static int ret = 0;
    public static  List<List<Integer>>NumberOfCombination(int[] nums)
    {
    	int numzero = 0, numone = 0;
    	for(int i : nums)
    	{
    		if(i==0)
    			numzero++;
    		if(i==1)
    			numone++;
    	}
    	List<Integer> path = new ArrayList<>();
    	List<List<Integer>> ret = new ArrayList<>();
    	NumberOfCombinationHelper(ret, path, numzero, numone, 0, nums);
    	return ret;
    }
    
    public static void NumberOfCombinationHelper(List<List<Integer>>ret, List<Integer> path, int numzero, int numone, int start, int[] nums)
    {
    	int n = nums.length;
    	if(numzero==0 && numone==0 && start ==n)
    	{
    		if(path!=null)
    		{
    			ret.add(new ArrayList<Integer>(path));
    		}
    		
    		return;
    	}
    	
    	for(int i=start; i<n; ++i)
    	{
    		if(isvalid(path, 0))
    		{
    			path.add(0);
    			NumberOfCombinationHelper(ret, path, numzero-1, numone, i+1, nums);
    			path.remove(path.size()-1);
    		}
    		
    		if(isvalid(path, 1))
    		{
    			path.add(1);
    			NumberOfCombinationHelper(ret, path, numzero, numone-1, i+1, nums);
    			path.remove(path.size()-1);
    		}
    	}
    }
    
    public static boolean isvalid(List<Integer> path,  int target)
    {
    	int count = 3;
    	for(int i=path.size()-1; i>=0 && count >0 && path.get(i)==target; --i, --count);
    	
    	return count != 0;
    }
    
    public int maximumGap(int[] nums) 
    {
    	int maxnum = 0, minnum = 0;
    	for(int num: nums)
    	{
    		maxnum = Math.max(maxnum, num);
    		minnum = Math.min(minnum, num);
    	}
    	
    	int interval = (maxnum - minnum)/nums.length+1;
    	int[] minbucket = new int[(maxnum-minnum)/interval+1];
    	for(int i=0; i<minbucket.length; ++i)
    		minbucket[i] = Integer.MAX_VALUE;
    	int[] maxbucket = new int[(maxnum-minnum)/interval+1];
    	for(int i=0; i<maxbucket.length; ++i)
    		maxbucket[i] = Integer.MIN_VALUE;
    	for(int i=0; i<nums.length; ++i)
    	{
    		int index = (nums[i]-minnum)/interval;
    		minbucket[index] = Math.min(minbucket[index], nums[i]);
    		maxbucket[index] = Math.max(maxbucket[index], nums[i]);
    	}
    	
    	int prev = 0;
    	int ret = 0;
    	for(int i=1; i<minbucket.length; ++i)
    	{
    		if(minbucket[i] == Integer.MIN_VALUE || maxbucket[i]==Integer.MAX_VALUE)
    			continue;
    		ret = Math.max(ret, minbucket[i]-maxbucket[prev]);
    		prev = i;
    	}
    	
    	return ret;
    }
    
    public static String convertToTitle(int n) 
    {
        StringBuilder ret = new StringBuilder();
        while(n > 0)
        {
        	char ch = (char)('A' + (n-1)%26);
        	ret.append( ch );
        	n = (n-1)/26;
        }
        
        ret.reverse();
        return ret.toString();
    }
    
    public class TreeNode
    {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	public TreeNode(int val) {
			// TODO Auto-generated constructor stub
    		this.val = val;
    		this.left = null;
    		this.right = null;
		}
    }
    public class BSTIterator {

    	private Stack<TreeNode> stack = new Stack<>();
    	private TreeNode cur;
        public BSTIterator(TreeNode root) {
            cur = root;
        }

        /** @return whether we have a next smallest number */
        public boolean hasNext() {
            return cur!=null || stack.isEmpty()==false;
        }

        /** @return the next smallest number */
        public int next() {
            while(cur!=null)
            {
            	stack.push(cur);
            	cur = cur.left;
            }
            
            TreeNode top = stack.pop();
            cur = top.right;
            return top.val;
        }
    }
    
    //Maximal Surpasser Count Problem
  public static int maxSurpasser(int[] nums)
  {
	  int n = nums.length;
	  int[] temp = new int[n];
	  int[] ret = new int[1];
	  HashMap<Integer, Integer> map = new HashMap<>();
	  sort(ret, nums, temp, map, 0, n-1);
	  System.out.println(map.size());
	  return ret[0];
  }
  
  public static void sort(int[] ret, int[] nums, int[] temp, HashMap<Integer, Integer> map, int start, int end)
  {
	  if(start >= end)
		  return;
	  int mid = start+(end-start)/2;
	  sort(ret, nums, temp, map, start, mid);
	  sort(ret, nums, temp, map, mid+1, end);
	  merge(ret, nums, temp, map, start, mid, end);
  }
  
  public static void merge(int[]ret, int[]nums, int[] temp, HashMap<Integer, Integer> map, int start, int mid, int end)
  {
	  int i = start, j = mid+1, k=start;
	  while(i<=mid && j<=end)
	  {
		  if(nums[i] < nums[j])
		  {
			  temp[k++] = nums[i];
			  if(map.containsKey(nums[i]))
				  map.put(nums[i], map.get(nums[i])+(end-j+1));
			  else
				  map.put(nums[i], end-j+1);
			  
			  ret[0] = Math.max(ret[0], map.get(nums[i]));
			  ++i;
		  }
		  else
		  {
			  temp[k++] = nums[j++];
		  }
	  }
	  
	  while(i<=mid)
	  {
		  temp[k++] = nums[i++];
	  }
	  
	  while(j<=end)
	  {
		  temp[k++] = nums[j++];
	  }
	  
	  for(int p = start; p<=end; ++p)
		  nums[p] = temp[p];
  }
    
    
 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    // end
	
}
