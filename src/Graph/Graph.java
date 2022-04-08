package Graph;

import java.util.LinkedList;
import java.util.List;

public class Graph {

	public int verticesNo;
	public int edgesNo;
	public boolean isDigraph;
	public List<Vertex> vertices;
	public LinkedList<Edge>[] adjacencylist;

	@SuppressWarnings("unchecked")
	public Graph(int vertices, int edges) {
		this.verticesNo = vertices;
		this.edgesNo = edges;
		adjacencylist = new LinkedList[vertices];
		for (int i = 0; i < vertices; i++) {
			adjacencylist[i] = new LinkedList<>();
		}
	}

	// add edge in both sides because it is undirected graph
	public void addEdge(Vertex source, Vertex target, int weight) {
		// Edge edge = new Edge(source, target, weight);
		source.addEdge(target, weight);
		adjacencylist[source.label].addFirst(new Edge(source, target, weight));
		if (isDigraph == false) {
			target.addEdge(source, weight);
			adjacencylist[target.label].addFirst(new Edge(target, source, weight));
		}
	}

}
