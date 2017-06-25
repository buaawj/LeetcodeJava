import java.security.KeyStore.Entry;
import java.util.*;

import javax.xml.soap.Node;

public class Graphs {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] pre = new int[][]
				{
					{1,0},{2,0},{3,1},{3,2}
				};
		int[] result = findOrder(4, pre);
		for(int i: result)
		System.out.println(i);
		
		// Valid Graph Tree test
		//int[][] edges = new int[][]{{0,1}, {0, 2}, {0,3}, {1, 4} };
		//int[][] edges = new int[][]{{0,1}, {1, 2}, {2,3}, {1, 3}, {1, 4} };
		
		int[][] edges = new int[][]{{0,1}, {1, 2}, {3, 4} };


		System.out.println(validTree(5, edges));
				
		/*int [][] edges1 = new int[][]{{0,1}, {0, 2}, {2, 3}, {0,4}, {2,5}, {5, 6}, {3, 7}, {6,8},{8,9},{9,10} };
		System.out.println(findMinHeightTrees2(11, edges1));*/
		int[][] grid = {{0, 0}, {0,1}, {1,2}, {2,1}};
		System.out.println(numIslands2(3, 3, grid));
	}
	
	public static class UndirectedGraphNode
	{
		int label;
		List<UndirectedGraphNode> neighbors;
		public UndirectedGraphNode(int val)
		{
			this.label = val;
			this.neighbors = new ArrayList<>();
		}
	}
	
	// Clone Graph
    public static UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        
    	Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    	return cloneGraphDfs(node, map);
    }
    
    public static UndirectedGraphNode cloneGraphDfs(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode>map)
    {
    	if(node == null)
    	{
    		return null;
    	}
    	
    	if(map.containsKey(node))
    	{
    		return map.get(node);
    	}
    	
    	UndirectedGraphNode newNode = new UndirectedGraphNode(node.label);
    	map.put(node, newNode);
    	
    	for(UndirectedGraphNode neighbor: node.neighbors)
    	{
    		newNode.neighbors.add(cloneGraphDfs(neighbor, map));
    	}
    	
    	return newNode;
    }
    
    public static UndirectedGraphNode cloneGraphBfs(UndirectedGraphNode node) {
        if(node == null)
        	return null;
        
    	Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
    	Queue<UndirectedGraphNode> queue = new LinkedList<UndirectedGraphNode>();
    	queue.add(node);
    	map.put(node, new UndirectedGraphNode(node.label));
    	while(!queue.isEmpty() )
    	{
    		UndirectedGraphNode topNode = queue.remove();
    		for(UndirectedGraphNode neighbor: topNode.neighbors )
    		{
    			if(!map.containsKey(neighbor))
    			{
    				map.put(neighbor, new UndirectedGraphNode(neighbor.label));
    				queue.add(neighbor);
    			}
    			
				map.get(topNode).neighbors.add(map.get(neighbor));

    		}
    	}
    	
    	return map.get(node);
    }
    
    
    public  static int[] findOrder(int numCourses, int[][] prerequisites) 
       {
       	if(numCourses == 0)
       		return new int[0];
       	int[] result = new int[numCourses];
       	if (prerequisites == null || prerequisites.length == 0) 
       	{
       		for (int i = 0; i < numCourses; i++) 
       		{
       			result[i] = i;
       		}
       		return result;
       	}
       	
    	ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    	for(int i=0; i<numCourses; ++i)
    	{
    		graph.add( new ArrayList<Integer>()); 
    	}
    	
    	// Build graph first
    	for(int i=0; i<prerequisites.length; ++i)
    	{
    		graph.get(prerequisites[i][1]).add(prerequisites[i][0]);
    	}
       	
       	int[] indegree = new int[numCourses];
       	Queue<Integer> queue = new LinkedList<>();
       	
       	for(int i=0; i<prerequisites.length; ++i)
       	{
       		indegree[prerequisites[i][0]]++;
       	}
       	
       	for(int i=0; i<numCourses; ++i)
       	{
       		if(indegree[i] == 0)
       		{
       			queue.offer(i);
       		}
       	}

       	int index = 0;
       	while(!queue.isEmpty())
       	{
       		int node = queue.poll();
       		result[index++] = node;
       		for(int neighbor : graph.get(node))
       		{
       			if(--indegree[neighbor] == 0)
       			{
       				queue.offer(neighbor);
       			}
       		}
       	}
       	

       		if(index != numCourses)
       			return new int[0];
       		
       	return result;
       }
       
    // Union Find
    public static class UnionFind
    {
    	private int[] parent;
    	private int[] rank;
    	private int count;
    	
    	public UnionFind(int n) {
    		parent = new int[n];
    		rank = new int[n];
			for(int i=0; i<n; ++i)
			{
				parent[i] = i;
				rank[i] = 0;
			}
			count = n;
		}
    	
    	public int Find(int x)
    	{
    		while(x!=parent[x])
    		{
    			x = parent[x];
    		}
    		
    		return x;
    	}
    	
    	public void Union(int x, int y)
    	{
    		int p1 = Find(x);
    		int p2 = Find(y);
    		
    		if(p1 == p2)
    			return;
    		
    		if(rank[p1] < rank[p2])
    		{
    			parent[p1] = p2;
    		}
    		else
    		{
    			parent[p2] = p1;
    	        if (rank[p1] == rank[p2]) 
    	        {
    	                rank[p1]++;
    	        }
    		}
    		--count;	
    	}
    }
    
    // Graph Valid tree
    public static boolean validTree(int n, int[][] edges)
    {
    	UnionFind uf = new UnionFind(n);
    	for(int i=0; i<edges.length; ++i)
    	{
    		if(uf.Find(edges[i][0]) == uf.Find(edges[i][1]))
    		{
    			return false;
    		}
    		
    		uf.Union(edges[i][0], edges[i][1]);
    	}
    	
    	return uf.count == 1;
    }
    
    //Minimum Height Trees
    public static List<Integer> findMinHeightTrees(int n, int[][] edges) 
    {
    	HashMap<Integer, Set<Integer>> graph = new HashMap<>();
    	List<Integer> res = new LinkedList<>();
    	
    	if(n == 1)
    	{
    		res.add(n-1);
    		return res;
    	}
    	
    	
    	for(int i=0; i<n; ++i)
    	{
    		graph.put(i, new HashSet<Integer>());
    	}
    	
    	// Build graph first
    	for(int i=0; i<edges.length; ++i)
    	{
    		int[] edge = edges[i];
    		graph.get(edge[0]).add(edge[1]);
    		graph.get(edge[1]).add(edge[0]);
    	}
    	
    	// Get all the leaf
    	Queue<Integer> leafs = new LinkedList<>();
    	for(int i=0; i<n; ++i)
    	{
    		if(graph.get(i).size() == 1)
    		{
    			leafs.offer(i);
    		}
    	}
    	
    	while(graph.size() > 2)
    	{
    		int sz = leafs.size();
    		for(int i=0; i<sz; ++i)
    		{
    			int leaf = leafs.poll();
    			int parent = graph.get(leaf).iterator().next();
    			graph.get(parent).remove(leaf);
    			if(graph.get(parent).size() == 1)
    			{
    				leafs.add(parent);
    			}
    		
    			graph.remove(leaf);
    		} 		
    	}
    	
    	for(int key : graph.keySet())
    	{
    		res.add(key);
    	}
    	
    	return res;
    }
    
    //Minimum Height Trees2
    public static List<Integer> findMinHeightTrees2(int n, int[][] edges) 
    {
    	HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>();
    	List<Integer> res = new LinkedList<>();
    	
    	if(n == 1)
    	{
    		res.add(n-1);
    		return res;
    	}
    	
    	int[] degree = new int[n];
    	
    	for(int i=0; i<n; ++i)
    	{
    		graph.put(i, new ArrayList<Integer>());
    	}
    	
    	
    	// Build graph first
    	for(int i=0; i<edges.length; ++i)
    	{
    		int[] edge = edges[i];
    		graph.get(edge[0]).add(edge[1]);
    		graph.get(edge[1]).add(edge[0]);
    	}
    	
    	// Get all the leaf
    	Queue<Integer> leafs = new LinkedList<>();
    	for(int i=0; i<n; ++i)
    	{
    		if(graph.get(i).size() == 1)
    		{
    			leafs.offer(i);
    		}
    		degree[i] = graph.get(i).size();
    	}
    	
    	while(n > 2)
    	{
    		int sz = leafs.size();
    		for(int i=0; i<sz; ++i)
    		{	
    			int leaf = leafs.poll();
    			for(int j=0; j<graph.get(leaf).size(); ++j)
    			{
        			if(--degree[graph.get(leaf).get(j)] == 1)
        			{
        				leafs.add(graph.get(leaf).get(j));
        			}
    			}

    			--n;
    		} 		
    	}
    	
    	for(int node : leafs)
    	{
    		res.add(node);
    	}
    	
    	return res;
    }
    
    // Number of connected islands 200
    public static int numIslands(int[][] grid) 
    {
    	int m = grid.length, n= grid[0].length;
    	boolean[][] visited = new boolean[m][n];
    	int count = 0;
    	
    	for(int i=0; i<m; ++i)
    	{
    		for(int j=0; j<n; ++j)
    		{
    			if(grid[i][j] == 1 && visited[i][j]==false)
    			{
    				dfs(grid, i, j, visited);
    				++count;
    			}
    		}
    	}
    	
    	return count;
    }
    
    public static void dfs(int[][] grid, int x, int y, boolean[][] visited)
    {
    	int m = grid.length, n= grid[0].length;
    	if(x>=m || x<0 || y>=n ||y<0 || visited[x][y] || grid[x][y]==0)
    	{
    		return;
    	}
    	
    	visited[x][y] = true;
    	int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    	for(int i=0; i<4; ++i)
    	{
    		int x1 = x+dir[i][0], y1 = y+dir[i][1];
    		dfs(grid, x1, y1, visited);
    	}
    }
    
    // 305 Number of Islands II
    public static List<Integer> numIslands2(int m, int n, int[][] positions) 
    {
    	UnionFind uf = new UnionFind(m*n);
    	
    	int count = 0;
    	List<Integer> res = new ArrayList<>();
    	boolean[][] visited = new boolean[m][n];
    	int[][] dir = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}  } ;
    	for(int i=0; i<positions.length; ++i)
    	{
    		++count;
    		int index = m*positions[i][0] + positions[i][1];
    		visited[positions[i][0]][positions[i][1]] = true;
    		for(int j=0; j<4; ++j)
    		{
    			int newx = positions[i][0]+dir[j][0];
    			int newy = positions[i][1]+dir[j][1];
   
    			if(newx >=m || newx <0 || newy>=n || newy <0 || !visited[newx][newy])
    			{
    				continue;
    			}
    			
    			if(uf.Find(newx*m +newy) != uf.Find(index))
    			{
    				uf.Union(newx*m +newy, index);
    				--count;
    			}
    		}
    		
    		res.add(count);
    	}
    	
    	return res;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
