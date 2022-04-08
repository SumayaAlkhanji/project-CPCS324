package Graph;


public class Edge implements Comparable<Edge> {
	public Vertex source;
	public Vertex target;
	public Vertex parent;
	public int weight;

	public Edge(Vertex source, Vertex target, int weight) {
		this.source = source;
		this.target = target;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge e) {
		return weight-e.weight;
	}
}
