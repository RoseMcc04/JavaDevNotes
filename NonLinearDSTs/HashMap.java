import LinearDSTs.LinkedList;

public class HashMap<K, V> 
{
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR = 0.75;

    private LinkedList<Entry<K, V>>[] buckets;
    private int size;
    private int capacity;

    public HashMap() 
    {
        this(DEFAULT_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashMap(int capacity) 
    {
        this.capacity = capacity;
        this.buckets = new LinkedList<>[capacity];
        this.size = 0;
    }

    public void insert(K key, V value) 
    {
        int index = getIndex(key);
        if (this.buckets[index] == null) this.buckets[index] = new LinkedList<>();
        LinkedList<Entry<K, V>> bucket = this.buckets[index];
        for (Entry<K, V> entry : bucket) 
        {
            if (entry.getKey().equals(key)) 
            {
                entry.setValue(value);
                return;
            }
        }
        bucket.insertAtTail(new Entry<>(key, value));
        this.size++;
        if ((double) this.size / this.capacity > LOAD_FACTOR) resize();
    }

    public V get(K key) 
    {
        int index = getIndex(key);
        if (this.buckets[index] != null) 
        {
            LinkedList<Entry<K, V>> bucket = this.buckets[index];
            for (Entry<K, V> entry : bucket) 
            {
                if (entry.getKey().equals(key)) return entry.getValue();
            }
        }
        return null;
    }

    public void remove(K key) 
    {
        int index = getIndex(key);
        LinkedList<Entry<K, V>> bucket = this.buckets[index];
        if (bucket != null) 
        {
            for (Entry<K, V> entry : bucket) 
            {
                if (entry.getKey().equals(key)) 
                {
                    bucket.remove(entry);
                    this.size--;
                    return;
                }
            }
        }
    }

    public int size() 
    {
        return this.size;
    }

    private int getIndex(K key) 
    {
        if (key == null) return 0;
        return Math.abs(key.hashCode() % this.capacity);
    }

    private void resize() 
    {
        int newCapacity = this.capacity * 2;
        LinkedList<Entry<K, V>>[] newBuckets = (LinkedList<Entry<K, V>>) new LinkedList[newCapacity];
        for (LinkedList<Entry<K, V>> bucket : this.buckets) 
        {
            if (bucket != null) 
            {
                for (Entry<K, V> entry : bucket) 
                {
                    int index = (entry.getKey() == null) ? 0 : Math.abs(entry.getKey().hashCode() % newCapacity);
                    if (newBuckets[index] == null) newBuckets[index] = new LinkedList<Entry<K, V>>();
                    newBuckets[index].insertAtTail(entry);
                }
            }
        }
        this.capacity = newCapacity;
        this.buckets = newBuckets;
    }

    @Override
    public String toString() 
    {
        StringBuilder sb = new StringBuilder();
        for (LinkedList<Entry<K, V>> bucket : this.buckets) 
        {
            if (bucket != null) 
            {
                for (Entry<K, V> entry : bucket) 
                {
                    sb.append(entry.toString()).append(", ");
                }
            }
        }
        if (sb.length() > 1) sb.setLength(sb.length() - 2);
        return sb.toString();
    }

    private static class Entry<K, V> 
    {
        private K key;
        private V value;

        public Entry(K key, V value) 
        {
            this.key = key;
            this.value = value;
        }

        public void setKey(K key) 
        {
            this.key = key;
        }

        public void setValue(V value) 
        {
            this.value = value;
        }

        public K getKey() 
        {
            return this.key;
        }

        public V getValue() 
        {
            return this.value;
        }

        @Override
        public String toString() 
        {
            return "{" + this.key.toString() + ", " + this.value.toString() + "}";
        }
    }
}