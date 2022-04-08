import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Random;

import Graph.Edge;
import Graph.Graph;
import Graph.Set;
import Graph.Vertex;

public class UtilityClass {
	static int[] nTrails = { 1000, 1000, 1000, 5000, 5000, 10000, 10000, 20000, 20000, 50000 };
	static int[] mTrails = { 10000, 15000, 25000, 15000, 25000, 1500, 25000, 200000, 300000, 1000000 };

	public static void saveToSheet(long[][] algorthimOnearray, String filename) throws Exception {

		// create the file to append data to it
		PrintWriter output = new PrintWriter(filename);

		// Print the header
		output.print("n,m,");
		for (int i = 0; i < algorthimOnearray[0].length; i++)
			output.print("T" + (i + 1) + ",");
		output.println("best,avg,worst");
		// Print the Body
		for (int i = 0; i < algorthimOnearray.length; i++) {
			output.print(nTrails[i] + "," + mTrails[i] + ",");
			long worst = algorthimOnearray[i][0];
			long best = algorthimOnearray[i][0];
			long avg = 0;
			for (int j = 0; j < algorthimOnearray[i].length; j++) {
				worst = Math.max(worst, algorthimOnearray[i][j]);
				best = Math.min(best, algorthimOnearray[i][j]);
				avg += algorthimOnearray[i][j];
				output.printf("%d,", algorthimOnearray[i][j]);
			}
			output.println(best + "," + avg / algorthimOnearray[i].length + "," + worst);
		}
		// Save the file
		output.close();

	}

	public static void PrintMinSpanTree(Set[] list) {
		int totalCost = 0;
		for (int i = 1; i < list.length; i++) {
			totalCost += list[i].weight;
		}
		System.out.println("MST Total Cost = " + totalCost);
	}

	public static void PrintMinSpanTree(LinkedList<Edge> list) {
		int totalCost = 0;
		for (int i = 0; i < list.size(); i++) {
			totalCost += list.get(i).weight;
		}
		System.out.println("MST Total Cost = " + totalCost);
	}

	public static void make_graph(Graph graph) {
		Random random = new Random();
		// ensure all edges are connected with at least one edge
		for (int i = 0; i < graph.verticesNo - 1; i++)
			graph.addEdge(new Vertex(i), new Vertex(i + 1), random.nextInt(10) + 1);
		// fill add the remaining edges
		int remaning = graph.edgesNo - (graph.verticesNo - 1);
		while (remaning > 0) {
			Vertex source = new Vertex(random.nextInt(graph.verticesNo));
			Vertex Destination = new Vertex(random.nextInt(graph.verticesNo));
			if (Destination != source && !exists(source, Destination, graph.adjacencylist[source.label])) {
				// add the edge with random weight
				graph.addEdge(source, Destination, random.nextInt(20) + 1);
				remaning--;
			}
		}
	}

	public static boolean exists(Vertex Source, Vertex Destination, LinkedList<Edge> list) {
		for (Edge edge : list) {
			if (edge.source == Source && edge.target == Destination) {
				return true;
			}
		}
		return false;
	}
}
