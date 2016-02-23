import edu.princeton.cs.algs4.*;
public class MyWeightedQuickUnionUF2 {
    private int[] parent;   // parent[i] = parent of i
    private int[] size;     // size[i] = number of sites in subtree rooted at i
	private int[] connection;
    private int count;      // number of components
	private int nu;

public int getNumberOfUnions(){
	return nu;
}

 public MyWeightedQuickUnionUF2(int N) {
        count = N;
		nu=0;
        parent = new int[N];
        size = new int[N];
		connection=new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            size[i] = 1;
			connection[i]=0;
        }
    }

    /**
     * Returns the number of components.
     *
     * @return the number of components (between <tt>1</tt> and <tt>N</tt>)
     */
    public int count() {
        return count;
    }
  
    /**
     * Returns the component identifier for the component containing site <tt>p</tt>.
     *
     * @param  p the integer representing one object
     * @return the component identifier for the component containing site <tt>p</tt>
     * @throws IndexOutOfBoundsException unless <tt>0 &le; p &lt; N</tt>
     */
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

    /**
     * Returns true if the the two sites are in the same component.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @return <tt>true</tt> if the two sites <tt>p</tt> and <tt>q</tt> are in the same component;
     *         <tt>false</tt> otherwise
     * @throws IndexOutOfBoundsException unless
     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site <tt>p</tt> with the 
     * the component containing site <tt>q</tt>.
     *
     * @param  p the integer representing one site
     * @param  q the integer representing the other site
     * @throws IndexOutOfBoundsException unless
     *         both <tt>0 &le; p &lt; N</tt> and <tt>0 &le; q &lt; N</tt>
     */
    public void union(int p, int q) {
		nu++;
		connection[p]=1;
		connection[q]=1;
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) return;

        // make smaller root point to larger one
        if (size[rootP] < size[rootQ]) {
            parent[rootP] = rootQ;
            size[rootQ] += size[rootP];
        }
        else {
            parent[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        count--;
		
		
			
		}

	public boolean isNonIsolated(){
		for(int i=0;i<connection.length;i++){
			if(connection[i]!=1)
				return false;
		}
		return true;
	}

	public boolean hasGiantComponent(int p){
		for(int i=0;i<size.length;i++){
			if(size[i]>(count/2))
				return true;
		}
		return(false);
	}	

	public boolean isConnected(){
		for(int i=1;i<parent.length;i++){
			if(!connected(0,i))
				return false;
		}	
			return true;
	}


    }




