public class ArrayQueue<T> 
{
    private Object[] queue;
    private int front, rear, size, capacity;

    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) 
    {
        this.capacity = initialCapacity;
        this.queue = (T[]) new Object[initialCapacity];
        this.front = 0;
        this.rear = 0;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    private void resize() 
    {
        int newCapacity = capacity * 2;
        T[] newQueue = (T[]) new Object[newCapacity];
        for (int i = 0; i < this.size; i++) newQueue[i] = (T) this.queue[(this.front + i) % this.capacity];
        this.queue = newQueue;
        this.front = 0;
        this.rear = this.size;
        this.capacity = newCapacity;
    }

    public void enqueue(T data) 
    {
        if (isFull()) resize();
        this.queue[this.rear] = (T) data;
        this.rear = (this.rear + 1) % this.capacity;
        this.size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() 
    {
        if (isEmpty()) return null;
        T item = (T) this.queue[this.front];
        this.queue[front] = null;
        this.front = (this.front + 1) % capacity;
        this.size++;
        return item;
    }

    public int size() 
    {
        return this.size;
    }

    public boolean isEmpty() 
    {
        return this.size == 0;
    }

    public boolean isFull() 
    {
        return this.size == this.capacity;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) return sb.toString("null");
        sb.append("null");
        for (int i = 0; i < this.size; i++) 
        {
            int index = (this.front + i) % this.capacity;
            sb.append(" <- ").append(this.queue[index]);
        }
        return sb.toString();
    }
}
