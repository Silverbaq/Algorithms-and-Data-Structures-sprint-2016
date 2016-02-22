import edu.princeton.cs.algs4.*;
// import java.util.*; 

public class GiantBook {

    public static void checkConnected (int N,int T) {
        int[] countUnionsConnectedT = new int[T];
        for(int i = 0; i < T; i++) {
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
            int countUnions = 0;

            while(uf.count() != 1) {
                countUnions++;
                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);
                if(!uf.connected(p, q) && (p != q)) uf.union(p, q);

            }

            countUnionsConnectedT[i] = countUnions;
            // StdOut.println("countUnionsConnectedT["+i+"] : " + countUnionsConnectedT[i]);

        }

        StdOut.printf("Connected -- Mean is: " + "%.2e\n", StdStats.mean(countUnionsConnectedT));
        StdOut.printf("Connected -- StdDev is: " + "%.2e\n", StdStats.stddev(countUnionsConnectedT));
    }

    public static void checkGiant (int N,int T) {
        int[] countUnionsGiantT = new int[T];
        for(int i = 0; i < T; i++) {
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
            int countUnions = 0;

            while(uf.hasNotGiant()) {
                countUnions++;
                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);
                if(!uf.connected(p, q) && (p != q)) uf.union(p, q);
            }

            countUnionsGiantT[i] = countUnions;
            // StdOut.println("countUnionsGiantT["+i+"] : " + countUnionsGiantT[i]);

        }

        StdOut.printf("Giant -- Mean is: " + "%.2e\n", StdStats.mean(countUnionsGiantT));
        StdOut.printf("Giant -- StdDev is: " + "%.2e\n", StdStats.stddev(countUnionsGiantT));

    }

    public static void checkIsolated (int N,int T) {
        int[] countUnionsIsolatedT = new int[T];
        for(int i = 0; i < T; i++) {
            WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
            int countUnions = 0;

            while(!uf.hasNotIsolated()) {
                countUnions++;
                int p = StdRandom.uniform(N);
                int q = StdRandom.uniform(N);
                if(!uf.connected(p, q) && (p != q)) uf.union(p, q);
            }

            countUnionsIsolatedT[i] = countUnions;
            // StdOut.println("countUnionsIsolatedT["+i+"] : " + countUnionsIsolatedT[i]);

        }

        StdOut.printf("Isolated -- Mean is: " + "%.2e\n", StdStats.mean(countUnionsIsolatedT));
        StdOut.printf("Isolated -- StdDev is: " + "%.2e\n", StdStats.stddev(countUnionsIsolatedT));
    }

	
	public static void main(String[] args) {
        StdOut.println("Insert problem input (N)");
        int N = StdIn.readInt();
        StdOut.println("Insert times of execution (T)");
        int T = StdIn.readInt();

        StdOut.println(N);
        StdOut.println(T);



        // check how many unions it takes to make all nodes connected
        checkConnected(N,T);
        
        // check how many unions it takes until a giant gets formed
        checkGiant(N,T);

        // check how many unions it takes until there are no more isolated nodes
        checkIsolated(N,T);
       
    }



}



