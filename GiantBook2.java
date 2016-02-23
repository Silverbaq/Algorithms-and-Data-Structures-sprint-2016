import edu.princeton.cs.algs4.*;
import java.util.*; 
public class GiantBook2 {




public static void main(String[] args){
		int T=2;
		int[] Tconnected=new int[T];
		
		int[] Tgiant=new int[T];
			for(int d=0;d<T;d++)
				StdOut.println("waaaaaaaaaaaaa "+Tgiant[d]);
		int[] TnonIsolated=new int[T];
		for(int j=0;j<T;j++){
  		//int N = StdIn.readInt();
		int N=4;
        MyWeightedQuickUnionUF2 uf = new MyWeightedQuickUnionUF2(N);
		Random r=new Random();
		
		int i=0;
      while (i<N) {
			i++;
            int p = r.nextInt(N);
            int q = r.nextInt(N);
			uf.union(p, q);
			StdOut.println(p+" - "+q);
			boolean connected=uf.isConnected();
			if(connected){
				StdOut.println("all components are connected -- number of unions : "+uf.getNumberOfUnions());
				if(Tconnected[j]==0)
					Tconnected[j]=uf.getNumberOfUnions();
					StdOut.println("ouuuuuuuuu");
			}
			boolean giant=uf.hasGiantComponent(p);
			if(giant){
				StdOut.println("Giant component found -- number of unions : "+uf.getNumberOfUnions());
				if(Tgiant[j]==0)
					Tgiant[j]=uf.getNumberOfUnions();
			}
			boolean iso=uf.isNonIsolated();
			if(iso){
				StdOut.println("Non isolation detected -- number of unions : "+uf.getNumberOfUnions());
				if(TnonIsolated[j]==0)
					TnonIsolated[j]=uf.getNumberOfUnions();
			}
			
        } 
		
           

        StdOut.println(uf.count() + " components");
		
    
		}
		StdOut.println("connected mean is "+mean(Tconnected));
		StdOut.println("connected standard dev is "+stddev(Tconnected));
		StdOut.println("giant mean is "+mean(Tgiant));
		StdOut.println("giant standard dev is "+stddev(Tgiant));
		StdOut.println("non isolated mean is "+mean(TnonIsolated));
		StdOut.println("non isolated standard dev is "+stddev(TnonIsolated));
	}

public static double mean(int[] a) {
        if (a.length == 0) return Double.NaN;
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum = sum + a[i];
        }
        return sum / a.length;
    }

  public static double var(int[] a) {
        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (a.length - 1);
    }

  public static double stddev(int[] a) {
        return Math.sqrt(var(a));
    }
}
