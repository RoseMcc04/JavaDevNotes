import java.util.Iterator;

public class LinkedQueue<T> implements Iterable<T>
{
    private LinkedList<T> l;

    public LinkedQueue() 
    {
        this.l = new LinkedList<T>();
    }

    public boolean enqueue(T data) 
    {
        if (data == null) return false;
        this.l.insertAtTail(data);
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
        sb.append("Front -> ");
        while (cursor != null) 
        {
            sb.append(cursor.getData()).append(" -> ");
            cursor = cursor.getLink();
        }
        sb.append(" -> Rear");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() 
    {
        return new LinkedQueueIterator();
    }

    private class LinkedQueueIterator implements Iterator<T> 
    {
        private LinkedList<T>.Node cursor;

        public LinkedQueueIterator() 
        {
            this.cursor = this.l.getHead();
        }

        @Override
        public boolean hasNext() 
        {
            return this.cursor != null;
        }

        @Override
        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            T data = cursor.getData();
            cursor = cursor.getLink();
            return data;
        }
    }
}
