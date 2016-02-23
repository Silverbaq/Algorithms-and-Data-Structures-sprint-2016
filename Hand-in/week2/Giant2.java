
import edu.princeton.cs.algs4.*;

public class Giant2 {


  public static void main(String[] args) {
      int N = StdIn.readInt(); // Amount of connections
      int T = StdIn.readInt(); // Times to repeat

      int[] giant = new int[T];

      int j = 0;

      while (j < T){

      Stopwatch timer = new Stopwatch();


        QuickFindUF uf = new QuickFindUF(N);

        boolean _giantComponenet = false;

        int i = 1;
        while (!_giantComponenet ) {
            int p = StdRandom.uniform(N);
            int q = StdRandom.uniform(N);

            //if (uf.connected(p, q)) continue;
            uf.union(p, q);



            // Giant Component
            // Check if the Largest tree is equal to N/2
            if (uf.getBiggest() >= (N+1)/2 && !_giantComponenet){
              StdOut.printf("Giant Component after %d connections\n", i);
              giant[j] = i;
              _giantComponenet = true;
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

  }

}
