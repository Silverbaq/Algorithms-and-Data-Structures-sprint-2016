import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.Queue;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;


public class SpanningUSA {

    private Edge[] edgeTo;        
    private double[] distTo;      
    private boolean[] marked;    
    private IndexMinPQ<Double> pq;


    public SpanningUSA(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)      
            if (!marked[v]) prim(G, v);      
    }


    // run Prim's algorithm in graph G, starting from vertex s
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            visit(G, v);
        }
    }

    
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.changeKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    // return the edges in the mst
    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

   //returns the sum of the edges in the mst
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }

   

    public static void main(String[] args){

		  HashMap<Integer, String> nameMap = new HashMap<>(); // for mapping vertex to string names
    	  HashMap<String, Integer> vertexMap = new HashMap<>(); // for mapping string names to vertex number

        ArrayList<Edge> edges = new ArrayList<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readLine();

            // if input is an edge
            if (s.contains("--")) {
                String[] from = s.split("--");
                String[] to = from[1].split(" \\[");

                int v = vertexMap.get(from[0].trim());
                int w = vertexMap.get(to[0].trim());
                double we = Double.parseDouble(to[1].substring(0, to[1].length()-1));

                Edge e = new Edge(v, w, we);
                edges.add(e);
            } else { // if input is a vertex
                nameMap.put(nameMap.size(), s.trim());
                vertexMap.put(s.trim(), vertexMap.size());
            }
        }

        // Building graph
        EdgeWeightedGraph G = new EdgeWeightedGraph(edges.size());
        for (Edge e : edges)
                G.addEdge(e);

        // Making the MST with Prims
        SpanningUSA mst = new SpanningUSA(G);

        // Print out result
        for (Edge e : mst.edges()) {
            StdOut.println("from "+nameMap.get(e.either()) +" to "+ nameMap.get(e.other(e.either())) + " :" + e.weight()+" miles");
        }
        StdOut.println("total distance is : "+ mst.weight());
    }

public static class EdgeWeightedGraph
{
private final int V; // number of vertices
private int E; // number of edges
private Bag<Edge>[] adj; // adjacency lists
public EdgeWeightedGraph(int V)
{
this.V = V;
this.E = 0;
adj = (Bag<Edge>[]) new Bag[V];
for (int v = 0; v < V; v++)
adj[v] = new Bag<Edge>();
}

public int V() { return V; }
public int E() { return E; }
public void addEdge(Edge e)
{
int v = e.either(), w = e.other(v);
adj[v].add(e);
adj[w].add(e);
E++;
}
public Iterable<Edge> adj(int v)
{ return adj[v]; }
public Iterable<Edge> edges()
{
Bag<Edge> b = new Bag<Edge>();
for (int v = 0; v < V; v++)
for (Edge e : adj[v])
if (e.other(v) > v) b.add(e);
return b;
}

}
public static class IndexMinPQ<Key extends Comparable<Key>> {
    private int maxN;        // maximum number of elements 
    private int N;           // current number of elements 
    private int[] pq;        
    private int[] qp;        
    private Key[] keys;      

   
    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        keys = (Key[]) new Comparable[maxN + 1];    
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];                   
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }
   
    public boolean isEmpty() {
        return N == 0;
    }

    public boolean contains(int i) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        return qp[i] != -1;
    }
    
    public int size() {
        return N;
    }

	 public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        if (!contains(i)) return;
        keys[i] = key;
        swim(qp[i]);
    }

    
    public void insert(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IndexOutOfBoundsException();
        N++;
        qp[i] = N;
        pq[N] = i;
        keys[i] = key;
        swim(N);
    }

	 private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


  
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

	private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

	   public int delMin() {
        if (N == 0) throw new IndexOutOfBoundsException();
        int min = pq[1];
        exch(1, N--);
        sink(1);
        assert min == pq[N+1];
        qp[min] = -1;        
        keys[min] = null;   
        pq[N+1] = -1;        
        return min;
    }
}


}
