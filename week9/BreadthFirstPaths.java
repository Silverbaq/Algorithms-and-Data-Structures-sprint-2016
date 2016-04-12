import java.util.*;
public class BreadthFirstPaths
{
private static final int INFINITY = Integer.MAX_VALUE;
private boolean[] marked; // Is a shortest path to this vertex known?
private int[] edgeTo; // last vertex on known path to this vertex
private int[] distTo; 
private final int s; // source
public BreadthFirstPaths(Graph G, int s)
{
marked = new boolean[G.V()];
edgeTo = new int[G.V()];
distTo = new int[G.V()];
this.s = s;
bfs(G, s);
}
private void bfs(Graph G, int s)
{
Queue<Integer> queue = new Queue<Integer>();
marked[s] = true; // Mark the source
queue.enqueue(s); // and put it on the queue.
for (int v = 0; v < G.V(); v++)
	distTo[v] = INFINITY;
distTo[s] = 0;
while (!queue.isEmpty())
{
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
public boolean hasPathTo(int v)
{ return marked[v]; }
public Iterable<Integer> pathTo(int v)
{
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
