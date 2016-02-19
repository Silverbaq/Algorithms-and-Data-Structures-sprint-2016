
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class GiantBook {



  public static void main(String[] args) {
      int N = StdIn.readInt(); // Amount of connections
      //int T = StdIn.readInt(); // Times to repeat

      Stopwatch timer = new Stopwatch();


        MyUnionFind uf = new MyUnionFind(N);

        boolean _connected = false;
        boolean _giantComponenet = false;
        boolean _nonisolated = false;

        int i = 1;
        while (i < N) {
            int p = StdRandom.uniform(N);
            int q = StdRandom.uniform(N);

            if (uf.connected(p, q)) continue;
            uf.union(p, q);

            // Connected
            if (uf.count() == 1 && !_connected){
              StdOut.printf("Connected after %d connections\n", i);
              _connected = true;
            }

            // Giant Component
            if (uf.count() < N/2 && _giantComponenet){
              StdOut.printf("Giant Component after %d connections\n", i);
              _giantComponenet = true;
            }

            // Nonisolated
            // TODO:

          i++;
        }

      double time = timer.elapsedTime();
      StdOut.println("" + time);
      StdOut.println("Done!");
  }

}
