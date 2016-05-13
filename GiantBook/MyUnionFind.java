public class MyUnionFind { // WeightedQuickUnionUF
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
    private int count;      // number of components

    int maxSize = 1;
    int singels;

    public MyUnionFind(int N) {
        count = N;
        parent = new int[N];
        size = new int[N];
        singels = N;

        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int maxComponentSize(){
        return maxSize;
    }

    public int lonelyComponents(){
        return singels;
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

        if (size[rootP] == 1) singels--;
        if (size[rootQ] == 1) singels--;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];

            if (size[rootQ] > maxSize) maxSize = size[rootQ]; // Checks if the size of the tree with root Q is the biggest
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];

            if (size[rootP] > maxSize) maxSize = size[rootP]; // Checks if the size of the tree with root P is the biggest
        }


        count--;
    }
}
