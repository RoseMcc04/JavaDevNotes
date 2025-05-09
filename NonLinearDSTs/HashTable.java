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
        this.table[index] = new LinkedList<T>.insertAtTail(data, null);
        this.size++;
    }

    public boolean remove(T data) 
    {
        int index = hash(data);
        LinkedList<T>.Node cursor = this.table[index].getHead();
        LinkedList<T>.Node prev = null;
        while (cursor != null) 
        {
            if (cursor.getData().equals(data))
            {
                if (prev == null) this.table[index].removeFromHead();
                else 
                {
                    prev.setLink(cursor.getLink());
                    this.size--;
                    return true;
                }
            }
            prev = cursor;
            cursor = cursor.getLink();
        }
        return false;
    }

    public boolean find(T data) 
    {
        int index = hash(data);
        for (LinkedList<T>.Node cursor = this.table[index].getHead(); cursor != null; cursor = cursor.getLink()) 
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
        return this.size == 0;
    }

    public int size() 
    {
        return this.size;
    }

    @Override
    public String toString() 
    {
        if (isEmpty()) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.table.length; i++) 
        {
            LinkedList<T>.Node cursor = this.table[i].getHead();
            while (cursor != null) 
            {
                sb.append(cursor.getData().toString());
                if (cursor.getLink() != null) sb.append(" -> ");
                cursor = cursor.getLink();
            }
        }
        sb.append("\n");
        return sb.toString();
    }
}