import edu.princeton.cs.algs4.*;
import java.util.*;
public class Runsort{
static Comparable[] aux;
public static void sort(Comparable[] a){
aux = new Comparable[a.length];
boolean b=true;
int low=0,mid=0,high=0;
while(!isSorted(a)){
	int i=0;
	while(i<a.length){
		if(b)
			low=i;
		int j=i+1;
		while(j<a.length&&(!less(a[j],a[i]))){
			i=j;
			j++;}
		if(b)
			mid=j-1;
		else{
			high=j-1;
			if(high < mid) 
				j=a.length; // if there is an odd number of runs do not merge the last one
			else
				merge(a,low,mid,high);
			}
		i=j;
		b=!b;
	}
}
}

public static void merge(Comparable[] a, int lo, int mid, int hi){ // merge from the book Algorithms, 4th ed.
int i = lo, j = mid+1;
for (int k = lo; k <= hi; k++)
aux[k] = a[k];
for (int k = lo; k <= hi; k++)
if (i > mid) a[k] = aux[j++];
else if (j > hi ) a[k] = aux[i++];
else if (less(aux[j], aux[i])) a[k] = aux[j++];
else a[k] = aux[i++];

}
private static boolean less(Comparable v, Comparable w)
{ return v.compareTo(w) <0; }
public static boolean isSorted(Comparable[] a){
    boolean sorted = true;
    for (int i = 1; i < a.length; i++) {
        if (less(a[i],a[i-1])) sorted = false;}
    return sorted;
}
public static void main(String[] args){
ArrayList<Comparable> list=new ArrayList<Comparable>();
while(!StdIn.isEmpty()){
Comparable st=(Comparable)StdIn.readString();
list.add(st);
}
Comparable[] a=new Comparable[list.size()];
for(int i=0;i<a.length;i++)
	a[i]=list.get(i);
sort(a);
for(int i=0;i<a.length;i++)
	StdOut.println(a[i]);
}
}
