package Graph;

public class MinHeap {

	public int capacity;
	public int currentSize = 0;
	public HeapNode[] MinHeapArr;
	public int[] indexes;

	public MinHeap(int capacity) {
		this.capacity = capacity;
		MinHeapArr = new HeapNode[capacity + 1];
		indexes = new int[capacity];
		MinHeapArr[0] = new HeapNode(Integer.MIN_VALUE, -1);
	}

	public void insert(HeapNode Node) {
		MinHeapArr[++currentSize] = Node;
		indexes[Node.vertex] = currentSize;
		bubbleUp(currentSize);
	}

	public void bubbleUp(int Number) {
		int parentIndex = Number / 2;
		int currentIndex = Number;
		while (currentIndex > 0 && MinHeapArr[parentIndex].key > MinHeapArr[currentIndex].key) {
			HeapNode currentNode = MinHeapArr[currentIndex];
			HeapNode parentNode = MinHeapArr[parentIndex];

			// swap
			indexes[currentNode.vertex] = parentIndex;
			indexes[parentNode.vertex] = currentIndex;
			swap(currentIndex, parentIndex);
			currentIndex = parentIndex;
			parentIndex = parentIndex / 2;
		}
	}

	public HeapNode extractMin() {
		HeapNode min = MinHeapArr[1];
		HeapNode lastNode = MinHeapArr[currentSize];
		indexes[lastNode.vertex] = 1;
		MinHeapArr[1] = lastNode;
		MinHeapArr[currentSize--] = null;
		sinkDown(1);
		return min;
	}

	public void sinkDown(int k) {
		int smallest = k;
		int leftChildIndex = 2 * k;
		int rightChildIndex = 2 * k + 1;
		if (leftChildIndex < heapSize() && MinHeapArr[smallest].key > MinHeapArr[leftChildIndex].key) {
			smallest = leftChildIndex;
		}
		if (rightChildIndex < heapSize() && MinHeapArr[smallest].key > MinHeapArr[rightChildIndex].key) {
			smallest = rightChildIndex;
		}
		if (smallest != k) {

			HeapNode smallestNode = MinHeapArr[smallest];
			HeapNode kNode = MinHeapArr[k];

			// swap the positions
			indexes[smallestNode.vertex] = k;
			indexes[kNode.vertex] = smallest;
			swap(k, smallest);
			sinkDown(smallest);
		}
	}

	public void swap(int i, int j) {
		HeapNode temp = MinHeapArr[i];
		MinHeapArr[i] = MinHeapArr[j];
		MinHeapArr[j] = temp;
	}

	public boolean isEmpty() {
		return currentSize == 0;
	}

	public int heapSize() {
		return currentSize;
	}

	public void decreaseKey(int newKey, int vertex) {
		// get the index which key's needs a decrease;
		int index = indexes[vertex];
		// get the node and update its value
		HeapNode node = MinHeapArr[index];
		node.key = newKey;
		bubbleUp(index);
	}
}
