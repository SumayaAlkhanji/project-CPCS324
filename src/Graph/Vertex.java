package Graph;

import java.util.LinkedList;

public class Vertex implements Comparable<Vertex> {
    public int label;
    public boolean isVisited;
    public LinkedList<Edge> adjList;

    public Vertex(int label) {
        this.label = label;
        this.isVisited = false;
        this.adjList = new LinkedList<Edge>();
    }

    public void addEdge(Vertex target, int weight) {
        Edge edge = new Edge(this, target, weight);
        this.adjList.add(edge);
    }

    @Override
    public int compareTo(Vertex o) {
        return this.label - o.label;
    }
}
