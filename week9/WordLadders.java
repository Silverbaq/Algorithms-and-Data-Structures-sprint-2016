import edu.princeton.cs.algs4.*;
import java.util.*;
public class WordLadders {
public static final int N=5;

public static boolean isEdgable(String s,String v){
	for(int i=1;i<s.length();i++){
		int x=v.indexOf(s.charAt(i));
		if(x!=-1)
			v= v.substring(0, x)+v.substring(x+1);
		else
			return false;
	}	
	return true;
}



public static void main(String[] args){

	StdOut.println(isEdgable("homas","ierre"));
	StdOut.println(isEdgable("homas","somat"));

	 In in = new In(args[0]);
     ArrayList<String> words = new ArrayList<String>();
     while (!in.isEmpty()) {
         String word = in.readString();
         words.add(word);       }
     StdOut.println("Finished reading word list");

	Graph g=new Graph(words.size());
	for(int i=0;i<words.size();i++){
		for(int j=0;j<words.size();j++){
			if(isEdgable(words.get(i),words.get(j)))
				g.addEdge(i,j);
		}
	}

	
	//while (!StdIn.isEmpty()) {
            String from = /*StdIn.readString();*/"shows";
            String to   = /*StdIn.readString();*/"ready";
            if (!words.contains(from)) throw new RuntimeException(from + " is not in word list");
            if (!words.contains(to))   throw new RuntimeException(to   + " is not in word list");

            BreadthFirstPaths bfs = new BreadthFirstPaths(g, words.indexOf(from));
            if (bfs.hasPathTo(words.indexOf(to))) {
                StdOut.println("length = " + bfs.distTo(words.indexOf(to)));
                for (int v : bfs.pathTo(words.indexOf(to))) {
                    StdOut.println(words.get(v));
                }
            }
            else StdOut.println("NOT CONNECTED");
            StdOut.println();
      //  }



}
}

