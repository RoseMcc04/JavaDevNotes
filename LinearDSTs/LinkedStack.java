import java.util.EmptyStackException;
import java.util.Random;

public class LinkedStack<T> 
{
    private LinkedList<T> l;
    private int length;

    public LinkedStack() 
    {
        this.l = new LinkedList<T>();
        this.length = 0;
    }

    public void push(T data) 
    {
        l.insertAtHead(data);
        this.length++;
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
        return this.length == 0;
    }

    public int size() 
    {
        return this.length;
    }

    @Override
    public String toString() 
    {
        LinkedList<T>.Node cursor = this.l.getHead();
        StringBuilder sb = new StringBuilder("\n---\n");
        while (cursor.getLink() != null) 
        {
            sb.append(cursor.getData()).append("\n---\n");
            cursor = cursor.getLink();
        }
        return sb.toString();
    }

    public static void main(String[] args) 
    {
        LinkedStack<Integer> ls = new LinkedStack<Integer>();
        Random rand = new Random();
        for (int i = 0; i < 15; i++) 
        {
            ls.push(rand.nextInt(100));
        }
        System.out.println(ls.toString());
        for (int i = 0; i < 6; i++) ls.pop();
        System.out.println(ls.toString());
    }
}
