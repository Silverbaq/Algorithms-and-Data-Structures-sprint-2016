import edu.princeton.cs.algs4.*;


public class WeightedQuickUnionUF {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    int largestComponentSize = 0;
    int amountOneTimeUnions = 0;

  
    public WeightedQuickUnionUF(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

   
    public int count() {
        return count;
    }
  
   
    public int find(int p) {
        validate(p);
        while (p != parent[p])
            p = parent[p];
        return p;
    }

    // validate that p is a valid index
    private void validate(int p) {
        int N = parent.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));  
        }
    }

   
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

   
    public void union(int p, int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;


        if((rootP == p) && (size[p] == 1))
            amountOneTimeUnions++;
        if((rootQ == q) && (size[q] == 1))
            amountOneTimeUnions++;


        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }

        if(largestComponentSize < (size[rootQ] + size[rootP]))
            largestComponentSize = size[rootP] + size[rootQ];

        count--;
    }

    // public boolean isConnected() {
    //     if(count == 1) return true;
    //     return false;
       
    // }
    
    public int maximumComponentSize () {
        return parent.length;
    }

    public boolean hasNotGiant() {
        if( largestComponentSize >= maximumComponentSize() / 2) return false;
        return true;
    }

    public boolean hasNotIsolated() {
        if( amountOneTimeUnions == maximumComponentSize() ) return true;
        return false;
    }

  

}