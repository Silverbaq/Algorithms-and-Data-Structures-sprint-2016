import edu.princeton.cs.algs4.*;
import java.util.*;
public class FancyRunsort{
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
		int lengthOfRun=1;
		while(j<a.length&&(!less(a[j],a[i]))){
			i=j;
			j++;
			lengthOfRun++;}
		if(lengthOfRun<8)
			insertionSort(a,8-lengthOfRun,j);
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

public static void insertionSort(Comparable[] a, int k,int s){//from the book Algorithms, 4th ed. (with small changes in the array indexes)
int N =0;													  // k is from where to begin the sort // s is the number of elements to sort
if(k+s<a.length)
N=k+s;
else
N=a.length;
for (int i = k+1; i < N; i++){
for (int j = i; j > k && less(a[j], a[j-1]); j--)
exch(a, j, j-1);
}

}
private static void exch(Comparable[] a, int i, int j)
{ Comparable t = a[i]; a[i] = a[j]; a[j] = t; }
public static void merge(Comparable[] a, int lo, int mid, int hi){
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
