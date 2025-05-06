import LinearDSTs.LinkedList;

/**
 * @author Rosalyn McCormack
 * @version May 2024
 */
public class HashTable<T>
{
    private LinkedList[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) 
    {
        this.size = 0;
        this.table = (LinkedList<T>[]) new LinkedList[initialCapacity];
        for (int i = 0; i < initialCapacity; i++) 
        {
            table[i] = new LinkedList<T>();
        }
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() 
    {
        int newCapacity = this.table.length * 2;
        LinkedList<T>[] newTable = (LinkedList<T>[]) new LinkedList[newCapacity];
        for (int i = 0; i < newTable.length; i++) 
        {
            newTable[i] = new LinkedList<T>();
        }
        for (int i = 0; i < this.table.length; i++) 
        {
            LinkedList<T> oldList = this.table[i];
            for (LinkedList<T>.Node cursor = oldList.getHead(); cursor.getLink() != null; cursor = cursor.getLink()) 
            {
                T data = cursor.getData();
                int newIndex = Math.abs(data.hashCode() % newCapacity);
                newTable[newIndex].insertAtTail(data);
            }
        }
        this.table = newTable;
    }

    public void insert(T data) 
    {
        if (find(data)) return;
        int index = hash(key);
        this.table[index] = new LinkedList<T>.Node(data, null);
    }

    public T remove() 
    {
        
    }

    public boolean find(T data) 
    {
        int index = hash(data);
        for (LinkedList<T>.Node cursor = this.table[index]; cursor != null; cursor = cursor.getLink()) 
        {
            if (cursor.getData().equals(data)) return true;
        }
        return false;
    }

    @SuppressWarnings("unused")
    private int hash(T data) 
    {
        return Math.abs(data.hashCode() % this.table.length);
    }

    public boolean isEmpty() 
    {
        return this.table.length == 0;
    }

    @Override
    public String toString() 
    {
        if (isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        for (LinkedList<T>.Node head : this.table) 
        {
            sb.append(head.getData());
            for (LinkedList<T>.Node cursor = head; cursor != null; cursor = cursor.getLink()) 
            {
                sb.append(" -> ").append(cursor.getData());
            }
        }
        return sb.toString();
    }
}