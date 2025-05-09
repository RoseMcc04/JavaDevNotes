import java.util.EmptyStackException;
import java.util.Random;
import java.util.Iterator;

public class LinkedStack<T> implements Iterable<T>
{
    private LinkedList<T> l;

    public LinkedStack() 
    {
        this.l = new LinkedList<T>();
    }

    public void push(T data) 
    {
        l.insertAtHead(data);
    }

    public T pop() 
    {
        if (isEmpty()) throw new EmptyStackException();
        T data = this.l.removeFromHead();
        return data;
    }

    public T peek() 
    {
        if (isEmpty()) throw new EmptyStackException();
        return this.l.getHead().getData();
    }

    public boolean isEmpty() 
    {
        return this.l.size() == 0;
    }

    public int size() 
    {
        return this.l.size();
    }

    public boolean contains(T data) 
    {
        LinkedList<T>.Node cursor = this.l.getHead();
        while (cursor != null) 
        {
            if (cursor.getData().equals(data)) return true;
            cursor = cursor.getLink();
        }
        return false;
    }

    @Override
    public String toString() 
    {
        LinkedList<T>.Node cursor = this.l.getHead();
        StringBuilder sb = new StringBuilder("\n---\n");
        while (cursor != null) 
        {
            sb.append(cursor.getData()).append("\n---\n");
            cursor = cursor.getLink();
        }
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() 
    {
        return new LinkedStackIterator();
    }

    private class LinkedStackIterator implements Iterator<T> 
    {
        private LinkedStack<T> cursor;

        public LinkedStackIterator() 
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
            T data = this.cursor.getData();
            cursor = cursor.getLink();
            return data;
        }
    }
}
