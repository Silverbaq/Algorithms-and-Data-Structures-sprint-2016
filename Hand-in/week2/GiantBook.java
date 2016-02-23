
import edu.princeton.cs.algs4.*;

public class GiantBook {


  public static void main(String[] args) {
      int N = StdIn.readInt(); // Amount of connections
      int T = StdIn.readInt(); // Times to repeat

      int[] iso = new int[T];
      int[] giant = new int[T];
      int[] connected = new int[T];

      int j = 0;

      while (j < T){

      Stopwatch timer = new Stopwatch();


        MyUnionFind uf = new MyUnionFind(N);

        boolean _connected = false;
        boolean _giantComponenet = false;
        boolean _nonisolated = false;

        int i = 1;
        while (!_connected || !_giantComponenet || !_nonisolated) {
            int p = StdRandom.uniform(N);
            int q = StdRandom.uniform(N);

            //if (uf.connected(p, q)) continue;
            uf.union(p, q);

            // Connected
            // Checks if the largest tree is equal to N
            if (uf.maxComponentSize() == N && !_connected){
              StdOut.printf("Connected after %d connections\n", i);
              connected[j] = i;
              _connected = true;
            }

            // Giant Component
            // Check if the Largest tree is equal to N/2
            if (uf.maxComponentSize() >= N/2 && !_giantComponenet){
              StdOut.printf("Giant Component after %d connections\n", i);
              giant[j] = i;
              _giantComponenet = true;
            }

            // Nonisolated
            // If the smallest tree is atlest 2
            if (uf.lonelyComponents() == 1 && !_nonisolated){
              StdOut.printf("Nonisolated after %d connections\n", i);
              iso[j] = i;
              _nonisolated = true;
            }

          i++;
        }

      double time = timer.elapsedTime();
      StdOut.println("" + time);
      StdOut.println("Done! #"+j);

      j++;
    }
    StdOut.printf("Giant Mean is: " + "%.2e\n", StdStats.mean(giant));
    StdOut.printf("Giant StdDev is: " + "%.2e\n", StdStats.stddev(giant));
    StdOut.printf("Nonisolated Mean is: " + "%.2e\n", StdStats.mean(iso));
    StdOut.printf("Nonisolated StdDev is: " + "%.2e\n", StdStats.stddev(iso));
    StdOut.printf("Connected Mean is: " + "%.2e\n", StdStats.mean(connected));
    StdOut.printf("Connected StdDev is: " + "%.2e\n", StdStats.stddev(connected));
  }

}
