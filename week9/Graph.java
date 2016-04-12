import java.util.*;
public class Graph
{
private final int V;
private int E;
private ArrayList<Integer>[] adj;
public Graph(int V)
{
this.V = V;
this.E = 0;
adj = (ArrayList<Integer>[]) new ArrayList[V];
for (int v = 0; v < V; v++)
adj[v] = new ArrayList<Integer>();
}
public int V() { return V; }
public int E() { return E; }
public void addEdge(int v, int w)
{
adj[v].add(w);
E++;
}
public Iterable<Integer> adj(int v)
{ return adj[v]; }

}
