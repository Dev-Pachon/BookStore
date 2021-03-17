package dataStructures;

import java.util.ArrayList;

public class Hash<T, K extends Comparable<K>> implements IHash<T, K> {

	private NodeHash<T,K>[] hashM;
	private int size;

	@SuppressWarnings("unchecked")
	public Hash(int size) {
		hashM = new NodeHash[size];
		size = 0;
	}

	@Override
	public void insert(T t, K k) {
		int h = hash(k);
		if(hashM[h]==null) {
			hashM[h] = new NodeHash<>(t,k);
			size ++;
		} else {
			NodeHash<T,K> actual = hashM[h];
			if(actual.getKey().compareTo(k)==0) {
				actual.setValue(t);
			}
			while(actual.getNext()!=null) {
				actual = actual.getNext();
			}
			actual.setNext(new NodeHash<T,K>(t, k));
			size ++;
		}
	}

	@Override
	public T search(K k) {
		int h = hash(k);
		T searched = null;
		if(hashM[h]!=null) {
			NodeHash<T,K> actual = hashM[h];
			while(actual!=null&&searched==null) {
				if(actual.getKey().compareTo(k)==0)
					searched = actual.getValue();
				else
					actual = actual.getNext();
			}
		}

		return searched;
	}

	@Override
	public void delete(K k) {
		int h = hash(k);
		if(hashM[h]!=null) {
			NodeHash<T,K> actual = hashM[h];
			boolean deleted = false;
			if(actual.getKey().compareTo(k)==0) {
				hashM[h] = actual.getNext();
				deleted = true;
			}

			while(actual.getNext()!=null&&!deleted) {
				if(actual.getNext().getKey().compareTo(k)==0) {
					actual.setNext(actual.getNext().getNext());
					deleted = true;
				}else
					actual = actual.getNext();
			}

			if(deleted) {
				size--;
			}
		}
	}

	@Override
	public int size() {
		return size;
	}

	private int hash(K k) {
		return Math.abs(k.hashCode()%hashM.length);
	}

	public ArrayList<T> getElements() {
		if(size==0) {
			return null;
		}else {
			ArrayList<T> temp = new ArrayList<>();

			for(int i = 0; i< hashM.length;i++) {
				if(hashM[i]!=null) {
					NodeHash<T,K> actual = hashM[i];
					for(;actual!=null;actual = actual.getNext()) {
						temp.add(actual.getValue());
					}
				}
			}

			return temp;
		}

	}

}

class NodeHash<T,K extends Comparable<K>> {

	private NodeHash<T,K> next;

	private T value;

	private K key;

	public NodeHash(T t, K k) {
		value = t;
		key = k;
		setNext(null);
	}

	public K getKey() {
		return key;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public NodeHash<T,K> getNext() {
		return next;
	}

	public void setNext(NodeHash<T,K> next) {
		this.next = next;
	}
}
