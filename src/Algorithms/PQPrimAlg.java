package Algorithms;

import java.util.Comparator;
import java.util.PriorityQueue;

import Graph.Edge;
import Graph.Graph;
import Graph.Pair;
import Graph.Set;

public class PQPrimAlg {
	public static Set[] run(Graph graph) {
		// make a list to know if the vertex has been included in the MST or not
		boolean[] inMST = new boolean[graph.verticesNo];
		Set[] set = new Set[graph.verticesNo];
		int[] key = new int[graph.verticesNo];
		// Initialize priority queue by using the key for comparing
		PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(graph.verticesNo,
				Comparator.comparingInt(o -> o.key));
		// initialize
		for (int i = 0; i < graph.verticesNo; i++) {
			key[i] = Integer.MAX_VALUE;
			set[i] = new Set();
		}

		// create the first pair to start running through the graph
		key[0] = 0;
		queue.add(new Pair<Integer, Integer>(key[0], 0));
		set[0] = new Set();

		// while priority queue is not empty
		while (!queue.isEmpty()) {
			// pop the minimum pair
			Pair<Integer, Integer> extractedMinPair = queue.poll();
			int minVertex = extractedMinPair.value;
			// flag the minVertex as included
			inMST[minVertex] = true;

			// iterate through all the adjacent vertices and update their keys
			for (Edge edge : graph.adjacencylist[minVertex]) {
				// skip if the adjacent is in the MST
				if (inMST[edge.target.label]) {
					continue;
				}
				int destination = edge.target.label;
				int newKey = edge.weight;
				// skip if the updated key weight/key is higher than the existing one
				if (newKey > key[destination]) {
					continue;
				}
				// add it to the priority queue
				queue.add(new Pair<>(newKey, destination));
				// update
				set[destination] = new Set(minVertex, newKey);
				key[destination] = newKey;

			}
		}
		return set;
	}
}
