import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

public class UFW {
	public static void main(String[] args) {
	    int N = StdIn.readInt();
	    WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
	    while (!StdIn.isEmpty()) {
	        int p = StdIn.readInt();
	        int q = StdIn.readInt();
	        if (uf.connected(p, q)) continue;
	        uf.union(p, q);
	        StdOut.println(p + " " + q);
	    }
	    StdOut.println(uf.count() + " components");
	    StdOut.println("Are 0 and 1 connected? >> " + uf.connected(0,1));
	}
}