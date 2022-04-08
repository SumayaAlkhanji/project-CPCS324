package Graph;

public class Set {
	public int parent = -1;
	public int weight;

	public Set(int parent, int weight) {
		this.parent = parent;
		this.weight = weight;
	}

	public Set() {
	}

}
