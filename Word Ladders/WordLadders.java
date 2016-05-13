import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class WordLadders {

    public static String insertionSort(char[] a) {//from the book
        // Sort a[] into increasing order.
        int N = a.length; // array length
        for (int i = 0; i < N; i++) {
            int min = i;
            for (int j = i + 1; j < N; j++)
                if (less(a[j], a[min])) min = j;
            exch(a, i, min);

        }
        return new String(a);
    }

    private static boolean less(char v, char w) {
        return (v < w);
    }

    private static void exch(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    private static String[] getCombinations(String s) {
// returns an array of all sorted 4 letters combinations from the input string
        ArrayList<String> combinations = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            String combination = "";
            for (int j = 0; j < s.length(); j++) {
                if (j != i) {
                    combination += s.charAt(j);
                }
            }

            String sorted = insertionSort(combination.toCharArray());
            if (!combinations.contains(sorted))
                combinations.add(sorted);
        }

        return combinations.toArray(new String[combinations.size()]);

    }


    public static void main(String[] args) {


        HashMap<String, ArrayList<String>> lookupTable = new HashMap<String, ArrayList<String>>();
        HashMap<String, Integer> wordIndex = new HashMap<String, Integer>();
        ArrayList<String> words = new ArrayList<String>();
        int count = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            words.add(word);
            wordIndex.put(word, count);
            count++;

            String[] combinations = getCombinations(word);
            for (int i = 0; i < combinations.length; i++) {
                if (!lookupTable.containsKey(combinations[i])) {
                    lookupTable.put(combinations[i], new ArrayList<String>());
                }
                ArrayList<String> list = lookupTable.get(combinations[i]);
                list.add(word);
                lookupTable.put(combinations[i], list);
            }

        }


        Graph g = new Graph(words.size());
        //create graph
        for (String word : words) {
            String fourLetters = word.substring(1);
            String sorted = insertionSort(fourLetters.toCharArray());
            // get all adjacent vertices
            ArrayList<String> adjacent = lookupTable.get(sorted);
            if (adjacent != null) {
                for (String adj : adjacent)
                    if (!adj.equals(word)) {
                        int a = wordIndex.get(word);
                        int b = wordIndex.get(adj);
                        g.addEdge(a, b);
                    }
            }


        }

        String from = args[0];
        String to = args[1];

        if (!words.contains(from) || !words.contains(to)) {
            StdOut.println(from + " and/or " + to + " are not in the graph");
        } else {
            BreadthFirstPaths bfs = new BreadthFirstPaths(g, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {
                for (int x : bfs.pathTo(words.indexOf(to))) {
                    StdOut.println(words.get(x));
                }
                StdOut.println("length from " + from + " to " + to + " is " + bfs.distTo(words.indexOf(to)));
            } else
                StdOut.println("vertices not connected");
        }

    }

    public static class BreadthFirstPaths // from the book
    {
        private static final int INFINITY = Integer.MAX_VALUE;
        private boolean[] marked; // Is a shortest path to this vertex known?
        private int[] edgeTo; // last vertex on known path to this vertex
        private int[] distTo;
        private final int s; // source

        public BreadthFirstPaths(Graph G, int s) {
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            distTo = new int[G.V()];
            this.s = s;
            bfs(G, s);
        }

        private void bfs(Graph G, int s) {
            Queue<Integer> queue = new Queue<Integer>();
            marked[s] = true; // Mark the source
            queue.enqueue(s); // and put it on the queue.
            for (int v = 0; v < G.V(); v++)
                distTo[v] = INFINITY;
            distTo[s] = 0;
            while (!queue.isEmpty()) {
                int v = queue.dequeue(); // Remove next vertex from the queue.
                for (int w : G.adj(v))
                    if (!marked[w]) // For every unmarked adjacent vertex,
                    {
                        edgeTo[w] = v; // save last edge on a shortest path,
                        marked[w] = true; // mark it because path is known,
                        distTo[w] = distTo[v] + 1;
                        queue.enqueue(w); // and add it to the queue.
                    }
            }
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public Iterable<Integer> pathTo(int v) {
            if (!hasPathTo(v)) return null;
            Stack<Integer> path = new Stack<Integer>();
            for (int x = v; x != s; x = edgeTo[x])
                path.push(x);
            path.push(s);
            return path;
        }

        public int distTo(int v) {
            return distTo[v];
        }
    }

    public static class Graph {
        private final int V;
        private int E;
        private ArrayList<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            this.E = 0;
            adj = (ArrayList<Integer>[]) new ArrayList[V];
            for (int v = 0; v < V; v++)
                adj[v] = new ArrayList<Integer>();
        }

        public int V() {
            return V;
        }

        public int E() {
            return E;
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }

    }
}

