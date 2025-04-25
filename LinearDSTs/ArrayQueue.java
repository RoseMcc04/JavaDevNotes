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
}
