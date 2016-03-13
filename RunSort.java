import edu.princeton.cs.algs4.*;
import java.util.*;
public class RunSort{
static Comparable[] aux;
public static void sort(Comparable[] a)
{
aux = new Comparable[a.length]; // Allocate space just once.
boolean b=true;
int low=0,mid=0,high=0;
while(!isSorted(a)){
int i=0;
while(i<a.length){
	if(b)
		low=i;
	int j=i+1;
	//String str=(String)a[i];
	//int lengthOfRun=0;
	while(j<a.length&&(less(a[i],a[j])||a[i].equals(a[j]))){
//		str+=a[j];
		i=j;
		j++;
		//lengthOfRun++;
}
	//if(lengthOfRun<8){
		//insertionSort(a,j);
	//}
	
	if(b)
		mid=j-1;
	if(!b){
		high=j-1;
		if(high < mid){ j=a.length;}
		else{
		StdOut.println(low+" "+mid+" "+high);
		merge(a,low,mid,high);}}
	i=j;
	b=!b;
		}	
	}
}
/*public static void insertionSort(Comparable[] a, int k){
int N =0;
if(k+8<a.length)
N=k+8;
else
N=a.length;
for (int i = 1; i < N; i++)
{ // Insert a[i] among a[i-1], a[i-2], a[i-3]... ..
for (int j = i; j > 0 && less(a[j], a[j-1]); j--)
exch(a, j, j-1);
}
}
private static void exch(Comparable[] a, int i, int j)
{ Comparable t = a[i]; a[i] = a[j]; a[j] = t; }*/
public static void merge(Comparable[] a, int lo, int mid, int hi)
{ // Merge a[lo..mid] with a[mid+1..hi].
int i = lo, j = mid+1;
for (int k = lo; k <= hi; k++) // Copy a[lo..hi] to aux[lo..hi].
aux[k] = a[k];
for (int k = lo; k <= hi; k++) // Merge back to a[lo..hi].
if (i > mid) a[k] = aux[j++];
else if (j > hi ) a[k] = aux[i++];
else if (less(aux[j], aux[i])) a[k] = aux[j++];
else a[k] = aux[i++];
for (int k = lo; k <= hi; k++) //printing
	StdOut.print(a[k]);
StdOut.println("");
}	
private static boolean less(Comparable v, Comparable w) // orEqual !!!!!! or non redondant input
{ return v.compareTo(w) <0; }
public static boolean isSorted(Comparable[] a){
    boolean sorted = true;        
    for (int i = 1; i < a.length; i++) {
        if (less(a[i],a[i-1])) sorted = false;}
    return sorted;
}
public static void main(String[] args){
//String[] a={"a","a","b","t","d","j","g","a","x","y","k"};
//{"b","x","a","d","f","k","b","a","g","c","j","u","y","z"};
ArrayList<String> list=new ArrayList<String>();

while(!StdIn.isEmpty()){
String st=StdIn.readString();
list.add(st);
}

String[] a=new String[list.size()];
for(int i=0;i<a.length;i++)
	a[i]=list.get(i);
sort(a);
for(int i=0;i<a.length;i++)
	StdOut.println(a[i]);
	}
}
