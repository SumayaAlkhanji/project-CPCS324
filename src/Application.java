import java.util.LinkedList;

import Algorithms.KruskalAlg;
import Algorithms.MHPrimAlg;
import Algorithms.PQPrimAlg;
import Graph.Edge;
import Graph.Graph;
import Graph.Set;
import java.util.Scanner;

public class Application {

	static int numberOfTrails = 10;
	static long[][] KruskalArray = new long[10][numberOfTrails];
	static long[][] priorityQueuePrimArarray = new long[10][numberOfTrails];
	static long[][] minHeapPrimArarray = new long[10][numberOfTrails];
	static int[] nTrails = { 1000, 1000, 1000, 5000, 5000, 10000, 10000, 20000, 20000, 50000 };
	static int[] mTrails = { 10000, 15000, 25000, 15000, 25000, 1500, 25000, 200000, 300000, 1000000 };

	public static void main(String[] args) throws Exception {

		for (int caseNumber = 0; caseNumber < KruskalArray.length; caseNumber++) {
			for (int trail = 0; trail < numberOfTrails; trail++)
				runTrail(trail, caseNumber);
		}
		UtilityClass.saveToSheet(KruskalArray, "Kruskal.csv");
		UtilityClass.saveToSheet(priorityQueuePrimArarray, "priorityQueuePrim.csv");
		UtilityClass.saveToSheet(minHeapPrimArarray, "minHeapPrim.csv");
		System.out.println("Done");
	}

	private static void runTrail(int trail, int caseNumber) {
           
    
       
        
		long KruskalStartTime, priorityQueuePrimStartTime, minHeapPrimStartTime;
		long KruskalEndTime, priorityQueuePrimEndTime, minHeapPrimEndTime;
		Graph graph = new Graph(nTrails[caseNumber], mTrails[caseNumber]);
		UtilityClass.make_graph(graph);
		// KruskalAlg
		KruskalStartTime = System.currentTimeMillis();
		LinkedList<Edge> kruskalResult = KruskalAlg.run(graph);
		KruskalEndTime = System.currentTimeMillis();
		// priorityQueuePrim
		priorityQueuePrimStartTime = System.currentTimeMillis();
		Set[] priorityQueuePrimResult = PQPrimAlg.run(graph);
		priorityQueuePrimEndTime = System.currentTimeMillis();
		// PrimMinHeap
		minHeapPrimStartTime = System.currentTimeMillis();
		Set[] minHeapPrimResult = MHPrimAlg.run(graph);
		minHeapPrimEndTime = System.currentTimeMillis();
                
		KruskalArray[caseNumber][trail] = (KruskalEndTime - KruskalStartTime);
		priorityQueuePrimArarray[caseNumber][trail] = (priorityQueuePrimEndTime - priorityQueuePrimStartTime);
		minHeapPrimArarray[caseNumber][trail] = (minHeapPrimEndTime - minHeapPrimStartTime);
//		 print the results to assure it is working correctly
                System.out.println("--------------KRUSKAL ALGORITM--------------");
		UtilityClass.PrintMinSpanTree(kruskalResult);
                System.out.println("Kruskal Algorithm Running Time: " + (KruskalEndTime - KruskalStartTime));
                 System.out.println("");
                System.out.println("--------------MIN-HEAP PRIM ALGORITHM--------------");
		UtilityClass.PrintMinSpanTree(minHeapPrimResult);
                System.out.println("Min-Heap Algorithm Running Time:" + (minHeapPrimEndTime - minHeapPrimStartTime));
                 System.out.println("");
                System.out.println("--------------PRIORITY QUEUE PIRM ALGORITHM--------------");
		UtilityClass.PrintMinSpanTree(priorityQueuePrimResult);
                System.out.println("priority Queue Prim Algorithm Running Time: " + (priorityQueuePrimEndTime - priorityQueuePrimStartTime));
                System.out.println("");
                System.out.println("Done Case-Number:"+(caseNumber+1)+" Trail:"+(trail+1));
                System.out.println("***************************************************************");
		
                
 // --------------------------------------------------------------------------------
 
        
    }
}
                

	


