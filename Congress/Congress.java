import edu.princeton.cs.algs4.*;

public class Congress {

    static int seatsLeft;
    static MaxPQ<State> pq;

    private static double calcRatio(int seats, int population){
        double D = Math.sqrt(seats*(seats+1));
        return population / D;
    }

    public static void main(String[] args) {
        int S = Integer.parseInt(StdIn.readLine());
        seatsLeft = Integer.parseInt(StdIn.readLine());
        pq = new MaxPQ<>(S);

        // Reading all the states
        // adding a seat to it, and adds it to the PQ
        while (!StdIn.isEmpty()) {
            State state = new State();

            state.name = StdIn.readLine();
            state.population = Integer.parseInt(StdIn.readLine());
            state.seat = 1;
            seatsLeft--;
            state.ratio = calcRatio(1, state.population);

            pq.insert(state);
        }

        // Handing out the seats by
        // Taking the state with the biggest ratio out of the MaxPQ
        // Add a seat to it, and recalculate the radio
        // Insert the state to the MaxPQ once again.
        while(seatsLeft != 0){
            State state = pq.delMax();
            state.seat++;
            state.ratio = calcRatio(state.seat, state.population);
            pq.insert(state);
            seatsLeft--;
        }

        while(!pq.isEmpty()){
            State state = pq.delMax();
            StdOut.println(state.name +" "+ state.seat);
        }
    }

}

class State implements Comparable<State> {
    public String name;
    public int population;
    public int seat;
    public double ratio;

    @Override
    public int compareTo(State o) {
        if (this.ratio > o.ratio) return 1;
        else if (this.ratio < o.ratio) return -1;
        else return 0;
    }
}