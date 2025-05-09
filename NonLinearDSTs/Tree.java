public class Tree<T extends Comparable<T>>
{
    public class Node<T> 
    {
        private T data;
        private Node<T> left;
        private Node<T> right;

        public Node(T data) 
        {
            this.data = data;
            this.left = null;
            this.right = null;
        }

        public void setData(T data) 
        {
            this.data = data;
        }

        public T getData() 
        {
            return this.data;
        }

        public void setLeft(Node<T> left) 
        {
            this.left = left;
        }

        public Node<T> getLeft() 
        {
            return this.left;
        }

        public void setRight(Node<T> right) 
        {
            this.right = right;
        }

        public Node<T> getRight() 
        {
            return this.right;
        }
    }

    private Node<T> root;

    public void insert(T data) 
    {
        this.root = insertRecursive(this.root, data);
    }

    private Node<T> insertRecursive(Node current, T data) 
    {
        if (current == null) return new Node(data);
        int cmp = data.compareTo(current.getData());
        if (cmp < 0) current.setLeft(insertRecursive(current.getLeft(), data));
        else if (cmp > 0) current.setRight(insertRecursive(current.getRight(), data));
        return current;
    }

    public void remove(T data) 
    {
        this.root = removeRecursive(this.root, data);
    }

    private Node<T> remove(Node<T> current, T data) 
    {
        if (current == null) return null;
        if (data.compareTo(current.getData()) < 0) current.setLeft(removeRecursive(current.getLeft(), data));
        else if data.compareTo(current.getData() > 0) current.setRight(removeRecursive(current.getRight(), data));
        else 
        {
            if (current.getLeft() == null) return current.getRight();
            else if (current.getRight() == null) return current.getLeft();
            T minValue = min(current.getRight());
            current.setData(minValue);
            current.setRight(removeRecursive(current.getRight, minValue));
        }
        return current;
    }

    public boolean contains(T data) 
    {
        return containsRecursive(this.root, data);
    }

    private boolean containsRecursive(Node<T> current, T data) 
    {
        if (current == null) return false;
        int cmp = data.compareTo(current.getData());
        if (cmp < 0) containsRecursive(current.getLeft(), value);
        else if (cmp > 0) return containsRecursive(current.getRight(), value);
        else 
        {
            return true;
        }
    }

    public void preOrderTraversal() 
    {
        preOrderRecursive(this.root);
        System.out.println();
    }

    private void preOrderRecursive(Node<T> current) 
    {
        if (current != null) 
        {
            System.out.print(current.getData().toString() + " ");
            preOrderRecursive(current.getLeft());
            preOrderRecursive(current.getRight());
        }
    }

    public void postOrderTraversal() 
    {
        postOrderRecursive(this.root);
        System.out.println();
    }

    private void postOrderRecursive(Node<T> current) 
    {
        if (current != null) 
        {
            postOrderRecursive(current.getLeft());
            postOrderRecursive(current.getRight());
            System.out.print(current.getData().toString() + " ");
        }
    }

    public void inOrderTraversal() 
    {
        inOrderRecursive(this.root);
        System.out.println();
    }

    private void inOrderRecursive(Node<T> current) 
    {
        inOrderRecursive(current.getLeft());
        System.out.print(current.getData.toString() + " ");
        inOrderRecursive(current.getRight());
    }

    public T min() 
    {
        if (this.root == null) return null;
        Node<T> current = this.root;
        while (current.getLeft() != null) current = current.getLeft();
        return current.getData();
    }

    public T max() 
    {
        if (this.root == null) return null;
        Node<T> current = this.root;
        while (current.getRight() != null) current = current.getRight();
        return current.getData();
    }
}