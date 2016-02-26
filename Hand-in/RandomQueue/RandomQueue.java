import java.util.Iterator;
import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class RandomQueue<Item> implements Iterable<Item> {

    private Item[] RQ;  // Uses Item, since it's a generic type.
    private int N = 0;  // Size of the RandomQueue
    private int last;   // Index of the last element in the array

    public RandomQueue(){ // create an empty random queue
      // Java is stupid, and you have to typecast the instance of Item
      RQ = (Item[]) new Object[2];
    }

    public boolean isEmpty(){ // is it empty?
      return N == 0;
    }

    public int size(){ // return the number of elements
      return N;
    }

    public void enqueue(Item item) {
  		// increase array size if necessary
  		if(N == RQ.length) resize(2 * RQ.length);
  		// just add a new item at the last available index the array
  		RQ[last++] = item;
  		N++;
	  }

    public Item sample(){ // return (but do not remove) a random item
      return RQ[StdRandom.uniform(N)];
    }

    public Item dequeue(){ //  remove and return a random item
      if (isEmpty()) throw new RuntimeException("Queue underflow");

      // get random element and overwrite index with last element
		  int i = StdRandom.uniform(N);
		  Item item = RQ[i];
		  RQ[i] = RQ[last-1];

      // Null last element value (delete) and update counters
		  RQ[last-1] = null;
		  N--;
		  last--;

      // shrink array if necessary
		  if (N > 0 && N == RQ.length/4) resize(RQ.length/2);
		  return item;
    }

    public Iterator<Item> iterator(){ // return an iterator over the items in random order
      return new RandomIterator();
    }

    // resize the array
  	private void resize(int s) {
  		assert s >= N; // Checks if the amount of elements, will fit into the new array size

  		Item temp[] = (Item[]) new Object[s];
  		for(int i = 0; i < N; i++) {
  			temp[i] = RQ[i];
  		}
  		RQ = temp;
  		last = N;
  	}

    // RandomIterator class
	private class RandomIterator implements Iterator<Item> {

		private Item[] copy = (Item[]) new Object[N];
		private int pos = 0;

		public RandomIterator() {
			// copy q
			for (int i = 0; i < last; i++) {
				copy[i] = RQ[i];
			}

			// make random permutation of copy
			for (int k = copy.length - 1; k > 0; k--) {
				int i = StdRandom.uniform(k);
				Item t = copy[i];
				copy[i] = copy[k];
				copy[k] = t;
			}
		}

		public boolean hasNext() {
			return pos < copy.length;
		}

		// return next elements. Most likely, elements get picked
		// more than once. Next works until every element has
		// been seen at least once
		public Item next() {
			if (!hasNext()) throw new NoSuchElementException();
			return copy[pos++];
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}
	}

    public static void main(String args[]) {
        // Build a queue containing the Integers 1,2,...,6:
        RandomQueue<Integer> Q = new RandomQueue<Integer>();

        for (int i = 1; i < 7; ++i) Q.enqueue(i);

        // autoboxing! cool!
        // Print 30 die rolls to standard output
        StdOut.print("Some die rolls: ");

        for (int i = 1; i < 30; ++i)
            StdOut.print(Q.sample() + " ");

        StdOut.println();
        // Let’s be more serious: do they really behave like die rolls?
        int[] rolls = new int[10000];
        for (int i = 0; i < 10000; ++i)
            rolls[i] = Q.sample();

        // autounboxing! Also cool!
        StdOut.printf("Mean (should be around 3.5): %5.4f\n", StdStats.mean(rolls));
        StdOut.printf("Standard deviation (should be around 1.7): %5.4f\n", StdStats.stddev(rolls));

        // Now remove 3 random values
        StdOut.printf("Removing %d %d %d\n", Q.dequeue(), Q.dequeue(), Q.dequeue());

        // Add 7,8,9
        for (int i = 7; i < 10; ++i) Q.enqueue(i);

        // Empty the queue in random order
        while (!Q.isEmpty())
            StdOut.print(Q.dequeue() + " ");
        StdOut.println();

        // Let’s look at the iterator. First, we make a queue of colours:
        RandomQueue<String> C = new RandomQueue<String>();
        C.enqueue("red");
        C.enqueue("blue");
        C.enqueue("green");
        C.enqueue("yellow");

        Iterator I = C.iterator();
        Iterator J = C.iterator();

        StdOut.print("Two colours from first shuffle: " + I.next() + " " + I.next() + " ");
        StdOut.print("\nEntire second shuffle: ");

        while (J.hasNext())
            StdOut.print(J.next() + " ");

        StdOut.print("\nRemaining two colours from first shuffle: " + I.next() + " " + I.next());
    }
}
