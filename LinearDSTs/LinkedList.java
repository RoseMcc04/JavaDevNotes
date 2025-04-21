public class LinkedList<T>
{
    private Node head;
    private int length;

    public LinkedList() 
    {
        this.head = null;
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

    public int getLength() 
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
        if (this.head.getLink() == null) this.head = null;
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
    }
}