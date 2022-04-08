package Algorithms;

import java.util.ArrayList;
import java.util.LinkedList;

import Graph.Edge;
import Graph.Graph;

public class KruskalAlg {

	public static LinkedList<Edge> run(Graph graph) {
		// create a list of all edges
		ArrayList<Edge> edges = new ArrayList<Edge>(graph.edgesNo);
		// insert all edges in one list
		for (LinkedList<Edge> node : graph.adjacencylist) {
			for (Edge edge : node)
				edges.add(edge);
		}
		// sort the edges based on weight in ascending order
		edges.sort(null);
		// create a parent []
		int[] parent = makeParentsList(graph.verticesNo);

		LinkedList<Edge> MST = new LinkedList<>();

		int currentIndex = 0;
		for (int i = 0; currentIndex < graph.verticesNo - 1 && i < edges.size(); i++) {
			// get the next lowest edge
			Edge edge = edges.get(i);

			int x = findParent(parent, edge.source.label);
			int y = findParent(parent, edge.target.label);
			// if adding this edge will make a cycle then skip it
			if (x != y) {
				currentIndex++;
				MST.add(edge);
				// link the two nodes in each other by linking the parents of each of them
				parent[findParent(parent, y)] = findParent(parent, x);

			}
		}
		return MST;
	}

	public static int[] makeParentsList(int numberOfVertices) {
		int[] list = new int[numberOfVertices];
		for (int i = 0; i < numberOfVertices; i++) {
			list[i] = i;
		}
		return list;
	}

	// find the parent of certain vertex
	public static int findParent(int[] parent, int vertex) {
		if (parent[vertex] == vertex) {
			return vertex;
		}
		return findParent(parent, parent[vertex]);

	}

}
