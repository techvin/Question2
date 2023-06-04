package question2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

// This class is used for setting source and destination vertices for a given edge
class Edge {
	int source, dest;

	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
	}
}

// This class is used to create a graph
class Graph implements GNode {
	String name;
	
	// intCharMap is a hashmap of integer against character
	static HashMap<Integer, Character> intCharMap = new HashMap<>();
	static {
		intCharMap.put(0, 'a');
		intCharMap.put(1, 'b');
		intCharMap.put(2, 'c');
		intCharMap.put(3, 'd');
		intCharMap.put(4, 'e');
		intCharMap.put(5, 'f');
		intCharMap.put(6, 'g');
		intCharMap.put(7, 'h');
		intCharMap.put(8, 'i');
		intCharMap.put(9, 'j');
	}
	
	// final result to be returned ArrayList<ArrayList<GNode>>
	static ArrayList<ArrayList<GNode>> charsList = new ArrayList<ArrayList<GNode>>();

	List<List<Integer>> adjList = null;

	Graph(List<Edge> edges, int n) {
		adjList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			adjList.add(new ArrayList<>());
		}
		for (Edge edge : edges) {
			adjList.get(edge.source).add(edge.dest);
		}
	}

	Graph() {};

	public List<List<Integer>> getAdjacencyList() {
		return adjList;
	}

	// function prints the adjacency list of the graph
	public void printAdjacencyList() {
		for (int i = 0; i < adjList.size(); i++) {
			System.out.print("Adjacency list of " + i + " = ");
			for (int j = 0; j < adjList.get(i).size(); j++) {
				System.out.print(adjList.get(i).get(j) + " ");
			}
			System.out.println();
		}
	}

	// function gets the leaf vertices which do not have any children
	public ArrayList<Integer> getNoChildVerticesList() {
		ArrayList<Integer> noChildVerticesList = new ArrayList<>();
		for (int i = 0; i < adjList.size(); i++) {
			if (adjList.get(i).size() == 0) {
				noChildVerticesList.add(i);
			}
		}
		return noChildVerticesList;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public GNode[] getChildren() {
		// TODO Auto-generated method stub
		return null;
	}

	// function which accepts a given GNode vertex and returns back the paths traversed till all leaf vertices
	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList paths(GNode vertex) {
		int n = adjList.size();
		ArrayList<Character> charArrayList = new ArrayList<>();
		ArrayList<Integer> noChildVerticesList = getNoChildVerticesList();
		for (Integer j : noChildVerticesList) {
			printAllPaths(Integer.parseInt(vertex.getName()), j, n);
		}
		return charsList;
	}

	public void printAllPaths(int s, int d, int n) {
		boolean[] isVisited = new boolean[n];
		ArrayList<Integer> pathList = new ArrayList<>();

		// add source to path[]
		pathList.add(s);

		// Call recursive utility
		printAllPathsUtil(s, d, isVisited, pathList);
	}

	public void printAllPathsUtil(Integer s, Integer d, boolean[] isVisited, List<Integer> localPathList) {

		ArrayList<GNode> charList = new ArrayList<>();
		if (s.equals(d) && localPathList.size() > 1) {
			for (Integer i : localPathList) {
				GNode vertex = new Graph();
				vertex.setName(String.valueOf(intCharMap.get(i)));
				charList.add(vertex);
			}
			charsList.add(charList);
			return;
		}

		isVisited[s] = true;

		for (Integer i : adjList.get(s)) {
			if (!isVisited[i]) {
				localPathList.add(i);
				printAllPathsUtil(i, d, isVisited, localPathList);
				localPathList.remove(i);
			}
		}

		isVisited[s] = false;
	}
}

class GraphQuestion2 {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<Edge> edges = Arrays.asList(new Edge(0, 1), new Edge(0, 2), new Edge(0, 3), new Edge(1, 4), new Edge(1, 5),
				new Edge(2, 6), new Edge(2, 7), new Edge(2, 8), new Edge(3, 9));

		int n = 10;
		
		GNode graph = new Graph(edges, n);
		
		for (int i = 0; i < n; i++) {
			GNode vertex = new Graph();
			vertex.setName(String.valueOf(i));
			Graph.charsList.clear();
			ArrayList<ArrayList<GNode>> pathsList = graph.paths(vertex);
			if (pathsList.size() > 0) {
				System.out.print("\npath(" + Graph.intCharMap.get(i) + ") = " + pathsList);
				for (int j = 0; j < pathsList.size(); j++) {
					System.out.println("\n Values of paths from path(" + Graph.intCharMap.get(i) + ") = "
							+ pathsList.get(j).stream().map(GNode::getName).collect(Collectors.toList()));
				}
			}
		}
	}
}