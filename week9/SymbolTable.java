public class SymbolTable
{
private Node first; // first node in the linked list
private class Node
{ // linked-list node
String key;
ArrayList<String> val;
Node next;
public Node(String key, ArrayList<String> val, Node next)
{
this.key = key;
this.val = val;
this.next = next;
}
}
public ArrayList<String> get(String key)
{ // Search for key, return associated value.
for (Node x = first; x != null; x = x.next)
if (key.equals(x.key))
return x.val; // search hit
return null; // search miss
}
public void put(String key, String val)
{ // Search for key. Update value if found; grow table if new.
for (Node x = first; x != null; x = x.next)
if (key.equals(x.key))
{ x.val.add(val); return; } // Search hit: update val.
ArrayList<String> list=new ArrayList<String>();
list.add(val);
first = new Node(key, list, first); // Search miss: add new node.
}
}
