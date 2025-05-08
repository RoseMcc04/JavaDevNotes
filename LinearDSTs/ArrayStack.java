import java.lang.reflect.*;
import java.util.EmptyStackException;

public class ArrayStack<T> implements Iterable<T> 
{
    private Object[] stack;
    private int length;

    public ArrayStack(int size) 
    {
        if (size < 0) throw new IllegalArgumentException(size + "  is less than the minimum length of 0");
        this.stack = new Object[size];
        this.length = 0;
    }

    @SuppressWarnings("unchecked")
    private void ensureCapacity() 
    {
        if (this.length == this.stack.length) 
        {
            T[] newStack = (T[]) Array.newInstance(this.stack.getClass().getComponentType(), this.stack.length * 2);
            System.arraycopy(this.stack, 0, newStack, 0, this.stack.length);
            this.stack = newStack;
        }
    }

    public void push(T item) 
    {
        ensureCapacity();
        this.stack[this.length] = item;
    }

    public void push(T item) 
    {
        ensureCapacity();
        this.stack[this.length++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() 
    {
        if (isEmpty()) throw new EmptyStackException();
        T item = (T) this.stack[--this.length];
        this.stack[this.length] = null;
        return item;
    }

    public T peek() 
    {
        if (isEmpty()) throw new EmptyStackException();
        return this.stack[this.length - 1];
    }

    public boolean isEmpty() 
    {
        return this.length == 0;
    }

    public int size() 
    {
        return this.length;
    }

    public boolean contains(T data) 
    {
        for (int i = 0; i < this.length; i++) 
        {
            if (this.stack[i].equals(data)) return true;
        }
        return false;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder("\n---\n");
        for (int i = 0; i < this.length; i++) sb.append(this.stack[i].toString() + "\n---\n");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() 
    {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<T> 
    {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() 
        {
            return currentIndex < this.length;
        }

        @Override
        public T next() 
        {
            if (!hasNext()) throw new NoSuchElementException();
            return (T) this.stack[currentIndex++];
        }
    }
}
