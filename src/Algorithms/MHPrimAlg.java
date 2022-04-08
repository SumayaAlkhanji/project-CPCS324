package Algorithms;
import Graph.*;

public class MHPrimAlg {

	public static Set[] run(Graph graph) {
		// make a list to know if the vertex is still in the heap or not
		boolean[] inHeap = new boolean[graph.verticesNo];
		Set[] set = new Set[graph.verticesNo];
		int[] key = new int[graph.verticesNo];
		// create HeapNode for all the vertices
		HeapNode[] HeapNodes = new HeapNode[graph.verticesNo];
		for (int i = 0; i < graph.verticesNo; i++) {
			HeapNodes[i] = new HeapNode();
			HeapNodes[i].vertex = i;
			HeapNodes[i].key = Integer.MAX_VALUE;
			set[i] = new Set();
			set[i].parent = -1;
			inHeap[i] = true;
			key[i] = Integer.MAX_VALUE;
		}

		HeapNodes[0].key = 0;
		// fill the MinHeap
		MinHeap minHeap = new MinHeap(graph.verticesNo);
		for (HeapNode node : HeapNodes)
			minHeap.insert(node);

		// while minHeap is not empty
		while (!minHeap.isEmpty()) {
			// extract the minimum
			HeapNode extractedMinHeapNode = minHeap.extractMin();
			// extracted vertex
			int extractedVertex = extractedMinHeapNode.vertex;
			// flag the minVertex as excluded for the heap
			inHeap[extractedVertex] = false;

			// iterate through all the adjacent vertices and update their keys
			for (Edge edge : graph.adjacencylist[extractedVertex]) {
				// skip if the adjacent is not in the Heap
				if (!inHeap[edge.target.label]) {
					continue;
				}
				int destination = edge.target.label;
				int newKey = edge.weight;
				// skip if the updated key weight/key is higher than the existing one
				if (newKey > key[destination]) {
					continue;
				}
				minHeap.decreaseKey(newKey, destination);
				// update the parent HeapNode for destination
				set[destination].parent = extractedVertex;
				set[destination].weight = newKey;
				key[destination] = newKey;

			}
		}
		return set;
	}

}
