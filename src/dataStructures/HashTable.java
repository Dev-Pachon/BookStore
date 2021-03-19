package dataStructures;


public class HashTable<K,V> implements IHash<K,V> {

	HashTableNode<K, V>[] slots;
	int maxs;
	int size;

	@SuppressWarnings("unchecked")
	public HashTable() {
		maxs = 100;
		slots = new HashTableNode[maxs];
	}

	public int getSize() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
	
	public int hash(K key) {
		int hashCod = key.hashCode();
		return Math.abs(hashCod % maxs);
	}

	public V get(K key) throws ClassCastException {

		int index = hash(key);

		HashTableNode<K, V> f = slots[index];
		while (f != null) {
			if (f.getKey().equals(key)) {
				return f.getValue();
			}
			f = f.getNext();
		}
		return null;
	}

	public V remove(K key) throws ClassCastException {
		int index = hash(key);
		HashTableNode<K, V> f = slots[index];
		if (f == null) {
			return null;
		}
		if (f.getKey().equals(key)) {
			V val = f.getValue();
			f = f.getNext();
			slots[index] = f;
			size--;
			return val;
		} else {
			HashTableNode<K, V> prev = null;
			while (f != null) {

				if (f.getKey().equals(key)) {
					prev.setNext(f.getNext());
					size--;
					return f.getValue();
				}
				prev = f;
				f = f.getNext();
			}
			size--;
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public void add(K key, V value) throws ClassCastException {

		int index = hash(key);

		HashTableNode<K, V> f = slots[index];
		HashTableNode<K, V> toAdd = new HashTableNode<K, V>(key, value);
		if (f == null) {
			slots[index] = toAdd;
			size++;

		} else {
			while (f != null) {
				if (f.getKey().equals(key)) {
					f.setValue(value);
					size++;
					break;
				}
				f = f.getNext();
			}
			if (f == null) {
				f = slots[index];
				toAdd.setNext(f);
				slots[index] = toAdd;
				size++;
			}
		}
		if ((1.0 * size) / maxs > 0.7) {
			// do something
			HashTableNode<K, V>[] tmp = slots;
			slots = new HashTableNode[maxs];
			maxs = 2 * maxs;

			for (HashTableNode<K, V> theE : tmp) {
				while (theE != null) {
					add(theE.getKey(), theE.getValue());
					theE.setNext(theE.getNext());
				}
			}

		}

	}

}

class HashTableNode<K,V> {

	private K key;
	private V value;
	private HashTableNode<K, V> next;

	public HashTableNode(K key, V value) {
		this.key = key;
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public HashTableNode<K, V> getNext() {
		return next;
	}

	public void setNext(HashTableNode<K, V> next) {
		this.next = next;
	}
}
