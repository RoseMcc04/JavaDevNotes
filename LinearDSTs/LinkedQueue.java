public class LinkedQueue<T> 
{
    private LinkedList<T> l;

    public LinkedQueue() 
    {
        this.l = new LinkedList<T>();
    }

    public boolean enqueue(T data) 
    {
        if (data == null) return false;
        if (!(data instanceof T)) return false;
        this.l.insertAtHead(data);
        return true;
    }

    public void dequeue() 
    {
        if (this.l.getHead() == null) return;
        this.l.removeFromHead();
    }

    public LinkedList<T>.Node getFront() 
    {
        if (isEmpty()) return null;
        return this.l.getHead();
    }

    public T getRear() 
    {
        if (isEmpty()) return null;
        LinkedList<T>.Node cursor = this.l.getHead();
        while (cursor.getLink() != null) 
        {
            cursor = cursor.getLink();
        }
        return cursor.getData();
    }

    public boolean isEmpty() 
    {
        return this.l.getLength() == 0;
    }

    public int size() 
    {
        return this.l.getLength();
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        LinkedList<T>.Node cursor = this.l.getHead();
        sb.append("<- null");
        while (cursor != null) 
        {
            sb.append(cursor.getData()).append(" <- ");
            cursor = cursor.getLink();
        }
        return sb.toString();
    }
}
