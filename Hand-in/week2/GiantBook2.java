import edu.princeton.cs.algs4.*;
import java.util.*; 
public class GiantBook2 {




public static void main(String[] args){
		int T=Integer.parseInt(args[1]);
		int[] Tconnected=new int[T];
		int[] Tgiant=new int[T];
		int[] TnonIsolated=new int[T];
		for(int j=0;j<T;j++){
		int N=Integer.parseInt(args[0]);
        MyWeightedQuickUnionUF2 uf = new MyWeightedQuickUnionUF2(N);
		Random r=new Random();
		boolean connected=false;
		boolean giant=false;
		boolean iso=false;
      while (!connected||!giant||!iso) {
            int p = r.nextInt(N);
            int q = r.nextInt(N);
			uf.union(p, q);
			connected=uf.isConnected();
			if(connected){
				if(Tconnected[j]==0){
					Tconnected[j]=uf.getNumberOfUnions();
					StdOut.println("is connected after "+uf.getNumberOfUnions());}
			}
			giant=uf.hasGiantComponent();
			if(giant){
				if(Tgiant[j]==0){
					Tgiant[j]=uf.getNumberOfUnions();
					StdOut.println("giant component after "+uf.getNumberOfUnions());}
					
			}
			iso=uf.isNonIsolated();
			if(iso){
				if(TnonIsolated[j]==0){
					TnonIsolated[j]=uf.getNumberOfUnions();
					StdOut.println("non isolated after "+uf.getNumberOfUnions());}
			}
			
        } 
		
           

     //   StdOut.println(uf.count() + " components");
		
    
		}
		StdOut.printf("connected mean is "+"%.2e\n",mean(Tconnected));
		StdOut.printf("connected standard dev is "+"%.2e\n",stddev(Tconnected));
		StdOut.printf("giant mean is "+"%.2e\n",mean(Tgiant));
		StdOut.printf("giant standard dev is "+"%.2e\n",stddev(Tgiant));
		StdOut.printf("non isolated mean is "+"%.2e\n",mean(TnonIsolated));
		StdOut.printf("non isolated standard dev is "+"%.2e\n",stddev(TnonIsolated));
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
