public class Graph
{
private final int V; // number of vertices
private int E; // number of edges
private SymbolTable adj; // adjacency lists
public Graph(int V)
{
this.V = V; this.E = 0;
adj = (Bag<String>[]) new Bag[V]; // Create array of lists.
for (int v = 0; v < V; v++) // Initialize all lists
adj[v] = new Bag<String>(); // to empty.
}

}
public int V() { return V; }
public int E() { return E; }
public void addEdge(String v, String w)
{
adj.put(v,w); // Add w to v’s list.
E++;
}
public Iterable<Integer> adj(String v)
{ return adj.get(v); }
}
