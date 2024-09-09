package assembly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class EulerianPath {

	DeBruijnGraph dbGraph;
	private List<String> path = new ArrayList<String>();

	String startVertex;
	String endVertex;
	public Map<String, Integer> outDegree = new HashMap<>();
	public Map<String, Integer> inDegree = new HashMap<>();
	public List<String> vertices;
	private Map<String, List<String>> adjList;

	public EulerianPath(DeBruijnGraph dbGraph) {
		this.dbGraph = dbGraph;
		adjList = this.dbGraph.graph.getAdjacencyList();
		vertices = new ArrayList<>(adjList.keySet());
		countInOutDegrees();
	}

	public void findStartVetrex() {
		for (String node : adjList.keySet()) {
			int out = outDegree.getOrDefault(node, 0);
			int in = inDegree.getOrDefault(node, 0);
			if (out - in == 1) {
				startVertex = node;
			} 
		}

	}

	public void countInOutDegrees() {
		for (String node : adjList.keySet()) {
			for (String neighbor : adjList.get(node)) {
				outDegree.put(node, outDegree.getOrDefault(node, 0) + 1);
				inDegree.put(neighbor, inDegree.getOrDefault(neighbor, 0) + 1);
			}
		}

	}

	private void dfs(String startNode) {
		Stack<String> stack = new Stack<>();
		
		stack.push(startNode);

		while (!stack.isEmpty()) {
			String node = stack.peek();
			if (adjList.containsKey(node) && !adjList.get(node).isEmpty()) {
				String next = adjList.get(node).remove(0);
				stack.push(next);
			} else {
				path.add(stack.pop());
			}
		}

		Collections.reverse(path);

	}

	public void findEulerianPath() {
		findStartVetrex();
		if (startVertex == null) {
			startVertex = adjList.keySet().iterator().next();
		}
		dfs(startVertex);
	}

	public String reconstructGenome() {
		findEulerianPath();
		if (path.isEmpty()) {
			return "";
		}
		
		StringBuilder genomeBuilder = new StringBuilder(path.get(0));

		for (int i = 1; i < path.size(); i++) {
			String currentNode = path.get(i);
			genomeBuilder.append(currentNode.charAt(currentNode.length() - 1));
		}

		return genomeBuilder.toString();

	}
}
