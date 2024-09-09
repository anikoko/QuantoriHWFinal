package assembly;

import java.util.*;

public class Graph {
	private Map<String, List<String>> adjList;

	// Constructor
	public Graph() {
		adjList = new HashMap<>();
	}

	// Add a vertex to the graph
	public void addVertex(String vertex) {
		adjList.putIfAbsent(vertex, new ArrayList<>());
	}

	// Add an directed edge to the graph
	public void addEdge(String source, String destination) {
		addVertex(source);
		addVertex(destination);
		adjList.get(source).add(destination);
	}

	// Remove a vertex and its associated edges
	public void removeVertex(int vertex) {
		adjList.values().forEach(e -> e.remove(Integer.valueOf(vertex)));
		adjList.remove(vertex);
	}

	// Remove an edge
	public void removeEdge(int source, int destination) {
		List<String> edgesFromSource = adjList.get(source);
		if (edgesFromSource != null) {
			edgesFromSource.remove(Integer.valueOf(destination));
		}
	}
	
	// Function to count the number of edges in the graph
    public int countEdges() {
        int count = 0;
        for (List<String> neighbors : adjList.values()) {
            count += neighbors.size();
        }
        return count;
    }

	// Get the adjacency list
	public Map<String, List<String>> getAdjacencyList() {
		return adjList;
	}
	
	// Print the graph
	public void printGraph() {
		for (var entry : adjList.entrySet()) {
			System.out.println("Vertex " + entry.getKey() + " is connected to: " + entry.getValue());
		}
	}
}