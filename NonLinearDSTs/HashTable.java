import LinearDSTs.LinkedList;

/**
 * @author Rosalyn McCormack
 * @version May 2024
 */
public class HashTable<T>
{
    private LinkedList[] table;

    @SuppressWarnings("unchecked")
    public HashTable(int initialCapacity) 
    {
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
            for (LinkedList<T>.Node cursor = oldList.getHead(); cursor.getLink() == null; cursor = cursor.getLink()) 
            {
                T data = cursor.getData();
                int newIndex = Math.abs(data.hashCode() % newCapacity);
                newTable[newIndex].insertAtTail(data);
            }
        }
        this.table = newTable;
    }

    private int hash(T data) 
    {
        return Math.abs(data.hashCode() % this.table.length);
    }
}