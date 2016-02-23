public class QuickFindUF {
    private int[] id;    // id[i] = component identifier of i
    private int count;   // number of components
    int[] size;
    int biggest;

    public QuickFindUF(int N) {
        count = N;
        size = new int[N];
        biggest = 1;

        id = new int[N];
        for (int i = 0; i < N; i++){
            id[i] = i;
            size[i] = 1;
          }
    }


    public int count() {
        return count;
    }


    public int find(int p) {
        validate(p);
        return id[p];
    }

    // validate that p is a valid index
    private void validate(int p) {
        int N = id.length;
        if (p < 0 || p >= N) {
            throw new IndexOutOfBoundsException("index " + p + " is not between 0 and " + (N-1));
        }
    }


    public boolean connected(int p, int q) {
        validate(p);
        validate(q);
        return id[p] == id[q];
    }

    public int getBiggest(){
      return biggest;
    }

    public void union(int p, int q) {
        validate(p);
        validate(q);
        int pID = id[p];   // needed for correctness
        int qID = id[q];   // to reduce the number of array accesses




        // p and q are already in the same component
        if (pID == qID) return;

        int countS = 0;

        for (int i = 0; i < id.length; i++){
            if (id[i] == pID){
              id[i] = qID;
            }
          }

          size[qID] += size[pID];
          if (size[qID] > biggest)
            biggest = size[qID];
          System.out.println("p: " + p + " q: " + q);
          System.out.println("size of qID " + size[qID] + " pID " + size[pID]);
          System.out.println("biggest: " + biggest);
        count--;
    }




}
