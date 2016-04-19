import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.List;


public class SpanningUSA {

    private Edge[] edgeTo;        // edgeTo[v] = shortest edge from tree vertex to non-tree vertex
    private double[] distTo;      // distTo[v] = weight of shortest such edge
    private boolean[] marked;     // marked[v] = true if v on tree, false otherwise
    private IndexMinPQ<Double> pq;


    public SpanningUSA(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)      // run from each vertex to find
            if (!marked[v]) prim(G, v);      // minimum spanning forest
    }


    // run Prim's algorithm in graph G, starting from vertex s
    private void prim(EdgeWeightedGraph G, int s) {
        distTo[s] = 0.0;
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    // scan vertex v
    private void scan(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;         // v-w is obsolete edge
            if (e.weight() < distTo[w]) {
                distTo[w] = e.weight();
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
                else pq.insert(w, distTo[w]);
            }
        }
    }

    // return the edges in a minimum spanning tree (or forest) as
    // an iterable of edges
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

    // Returns the sum of the edge weights in a minimum spanning tree (or forest).
    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }

    static ST<Integer, String> nameMap = new ST<>(); // for mapping vertex to string names
    static ST<String, Integer> vertexMap = new ST<>(); // for mapping string names to vertex number

    public static void main(String[] args) {
        // Reads input, and maps it

        List<Edge> edges = new ArrayList<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readLine();

            // Splits the input
            if (s.contains("--")) {
                String[] es = s.split("--");
                String[] ee = es[1].split(" \\[");

                int v = vertexMap.get(es[0].trim());
                int w = vertexMap.get(ee[0].trim());
                double we = Double.parseDouble(ee[1].substring(0, ee[1].length() - 1));

                Edge e = new Edge(v, w, we);
                edges.add(e);
            } else { // Maps the input
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
            StdOut.println(nameMap.get(e.either()) +" - "+ nameMap.get(e.other(e.either())) + " " + e.weight());
        }
        StdOut.printf("%.2f\n", mst.weight());
    }

}
