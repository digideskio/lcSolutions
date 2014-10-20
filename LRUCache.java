// best way is to implement a double-linked list; a lot of coding....
// be careful with head and tail cases!

public class LRUCache {
    
    HashMap<Integer, Entry> map;
    LinkedList<Entry> cache;
    int capacity;
    
    class Entry {
        int key;
        int value;
        Entry (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
     public LRUCache(int capacity) {
        map = new HashMap<Integer, Entry>();
        cache = new LinkedList<Entry>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Entry val = map.get(key);
        if (val != null) {
            moveToFront(val);
            return val.value;
        } else {
            return -1;
        }
    }

    public void moveToFront(Entry entry) {
        cache.remove(entry);
        cache.addFirst(entry);
    }

    public void insert(int key, int value) {
        if (map.size() >= capacity) {
            Entry entry = cache.removeLast();
            map.remove(entry.key);
        }
        Entry newEntry = new Entry(key, value);
        map.put(key, newEntry);
        cache.addFirst(newEntry);
    }

    public void set(int key, int value) {
        Entry entry = map.get(key);
        if (entry != null) {
            entry.value = value;
            moveToFront(entry);
        } else {
            insert(key, value);
        }
    }
}