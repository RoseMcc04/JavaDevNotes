import java.util.Iterator;

public class LinkedList<T> implements Iterable<T>
{
    private Node head;
    private int length;

    public LinkedList() 
    {
        this(null);
        this.length = 0;
    }

    public LinkedList(Node head) 
    {
        if (head == null) return;
        this.head = head;
        this.length = 1;
    }

    public LinkedList(T data) 
    {
        if (data == null) return;
        this.head = new Node(data, null);
        this.length = 1;
    }

    public void insertAtHead(T data) 
    {
        if (data == null) return;
        Node newHead = new Node(data, this.head);
        this.head = newHead;
        this.length++;
    }

    public T removeFromHead() 
    {
        if (this.head == null || this.length == 0) return null;
        Node newHead = this.head.getLink();
        T data = this.head.getData();
        this.head = newHead;
        this.length--;
        return data;
    }

    public Node getHead() 
    {
        if (this.length == 0) 
        {
            return null;
        }
        return this.head;
    }

    public int size() 
    {
        return this.length;
    }

    public void insertAtTail(T data) 
    {
        if (data == null) return;
        Node newNode = new Node(data, null);
        if (head == null) this.head = newNode;
        else 
        {
            Node cursor = this.head;
            while (cursor.getLink() != null) 
            {
                cursor = cursor.getLink();
            }
            cursor.setLink(newNode);
        }
        this.length++;
    }

    public void removeFromTail() 
    {
        if (this.head == null) return;
        if (this.head.getLink() == null) 
        {
            this.head = null;
            this.length--;
            return;
        }
        else 
        {
            Node cursor = this.head;
            while (cursor.getLink().getLink() != null) 
            {
                cursor = cursor.getLink();
            }
            cursor.setLink(null);
            this.length--;
        }
    }

    public boolean remove(T data) 
    {
        Node cursor = this.head;
        Node prev = null;
        if (cursor == null) return false;
        if (cursor.getData().equals(data)) 
        {
            this.head = cursor.getLink();
            this.length--;
            return true;
        }
        while ((cursor != null) && !cursor.getData().equals(data)) 
        {
            prev = cursor;
            cursor = cursor.getLink();
        }
        prev.setLink(cursor.getLink());
        this.length--;
        return true;
    }

    public void reverse() 
    {
        Node prev = null;
        Node current = this.head;
        Node next = null;
        while (current != null) 
        {
            next = current.getLink();
            current.setLink(prev);
            prev = current;
            current = next;
        }
        this.head = prev;
    }

    public boolean contains(T data) 
    {
        Node cursor = this.head;
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
        StringBuilder sb = new StringBuilder();
        Node cursor = this.head;
        while (cursor != null)
        {
            sb.append(cursor.getData()).append(" -> ");
            cursor = cursor.getLink();
        }
        sb.append("null");
        return sb.toString();
    }

    @Override
    public Iterator<T> iterator() 
    {
        return new Iterator();
    }

    private class NodeIterator implements Iterator<T> 
    {
        private Node cursor;

        public NodeIterator() 
        {
            this.cursor = this.head;
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
            this.cursor = this.cursor.getLink();
            return data;
        }
    }

    public class Node
    {
        private Node link;
        private T data;

        public Node() 
        {
            this.link = null;
            this.data = null;
        }

        public Node(T data, Node link) 
        {
            this.link = link;
            this.data = data;
        }

        public void setData(T data) 
        {
            this.data = data;
        }

        public T getData() 
        {
            return this.data;
        }

        public void setLink(Node link) 
        {
            this.link = link;
        }

        public Node getLink() 
        {
            return this.link;
        }

        @Override
        public Node toString() 
        {
            return this.data.toString();
        }
    }
}