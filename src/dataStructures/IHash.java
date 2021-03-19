package dataStructures;

public interface IHash<K,V> {
	
	public int getSize();

    public boolean isEmpty();

    public int hash(K key);

    public V get(K key);

    public V remove(K key);

    public void add(K key, V value);
	
}
