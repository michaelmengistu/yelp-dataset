
//Java program to find shortest path in an undirected graph 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class ShortestPathBFS { 
	
	private static HashMap<String, ArrayList<String[]>> adj;
	private static String source, dest;
	
	public ShortestPathBFS(HashMap<String, ArrayList<String[]>> ulist, String src, String dst) {
		adj = ulist;
		source = src;
		dest = dst;
	}
	
	 /**
	  * function to print the shortest distance and path between source vertex and destination vertex 
	  * @return an ArrayList<String> of the shortest path of vertices (NOT edges)
	  */
	public ArrayList<String> getShortestDistance() 
	{ 
	    // predecessor[i] array stores predecessor of i
		// distance array stores distance of i from s 
	    HashMap<String, String> pred = new HashMap<String, String>();
	    HashMap<String, Integer> dist = new HashMap<String, Integer>();

	
	    if (BFS(pred, dist) == false) { 
	    	System.out.println("Given source and destination are not connected"); 
	        return null; 
	    } 
	
	    // LinkedList to store path 
	    LinkedList<String> path = new LinkedList<String>(); 
	    String crawl = dest; 
	    path.add(crawl); 
	     
	    while (pred.get(crawl) != null) 
	    { 
	        path.add(pred.get(crawl)); 
	        crawl = pred.get(crawl); 
	    } 
	
	    // Print path 
	    System.out.println("Business Path is ::"); 
	    ArrayList<String> buildPath = new ArrayList<String>();
	    for (int i = path.size() - 1; i >= 0; i--) { 
	        System.out.print(path.get(i) + "   "); 
	        buildPath.add(path.get(i));
	    } 
	    System.out.println();
	    return buildPath;
	} 

	/**
	 * a modified version of BFS that stores predecessor of each vertex in HashMap pred and its distance from source in HashMap dist 
	 * @param pred - hashmap of <String, String> which holds the previous vertex (value) of the key
	 * @param dist - hashmap of <String, Integer> which holds the distance (value) from the String vertex (key)
	 * @return boolean whether the path can be made
	 */
	private static boolean BFS(HashMap<String, String> pred, HashMap<String, Integer> dist) 
	{ 
	    // a queue to maintain vertices whose adjacency list is to be scanned as per normal BFS algorithm using LinkedList of String type 
	    LinkedList<String> queue = new LinkedList<String>(); 
	
	    // visited stores the information whether the vertex (String key) is reached at least once in the Breadth first search  
	    HashMap<String, Boolean> visited = new HashMap<String, Boolean>();
	    
	    // initially all vertices are unvisited  	    
	    adj.forEach((key, vertices) -> {
	    	visited.put(key, false);
	    	dist.put(key, Integer.MAX_VALUE);
	    	pred.put(key, null);
	    });
	
	    // now source is first to be visited and distance from source to itself should be 0 
	    visited.replace(source, true);
	    dist.replace(source, 0); 
	    queue.add(source); 
	
	    // bfs Algorithm 
	    while (!queue.isEmpty()) { 
	    	String v1 = queue.remove(); 
	    	
	    	ArrayList<String[]> v2List = adj.get(v1);
	    	
	        for (int i = 0; i < v2List.size(); i++) { 
    	        String v2 = v2List.get(i)[0];
	        	
	        	if (visited.get(v2) == false) {  
	        		visited.replace(v2, true);
	        		dist.replace(v2, dist.get(v1) + 1);
	        		pred.replace(v2, v1);
	                queue.add(v2); 
	                // stopping condition (when we find our destination) 
	                if (v2.equals(dest)) 
	                    return true; 
	            } 
	        } 
	    } 
	     return false; 
	 } 
} 
// This code is contributed by Sahil Vaid 
// modified by Blaine Britton