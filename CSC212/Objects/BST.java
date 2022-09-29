
public class BST<K extends Comparable<K>, T> implements Map<K, T> {
	BSTNode<K, T> root, current;

	@Override
	public boolean empty() {
		return root == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void update(T e) {
		current.data = e;
	}

	@Override
	public Pair<Boolean, Integer> find(K key) {
		BSTNode<K, T> p = root;
		IntegerWrapper x = new IntegerWrapper();

		while(p != null) {
			x.Increment();
			if(p.key.compareTo(key) == 0) {
				current = p;
				return new Pair<Boolean, Integer>(true, x.get());
			}
			else if(p.key.compareTo(key)>0)
				p = p.left;
			else
				p = p.right;
		}

		return new Pair<Boolean, Integer>(false, x.get());
	}

	@Override
	public Pair<Boolean, Integer> insert(K key, T data) {
		BSTNode<K,T> a = current;
		Pair<Boolean, Integer> pair = find(key);
		if(pair.first) {
			current = a;
			return new Pair<Boolean, Integer>(false, pair.second);
		}

		BSTNode<K,T> p = root, q = root;
		IntegerWrapper x = new IntegerWrapper(pair.second);
		BSTNode<K, T> temp = new BSTNode<K, T>(key, data);

		if (empty()) 
			root = current = temp;
		
		else {
		while(p != null) {
			q = p;

			if(p.key.compareTo(key)>0)
				p = p.left;
			else
				p = p.right;

			x.Increment();
		}

		if(q.key.compareTo(key)>0)
			current = q.left = temp;
		else
			current = q.right = temp;
		
		x.Increment();
		}

		return new Pair<Boolean, Integer>(true, x.get());
	}


	@Override
	public Pair<Boolean, Integer> remove(K key) { 
		IntegerWrapper x = new IntegerWrapper();
		K k1 = key;    
		BSTNode<K, T> p = root, q = null;     

		while (p != null) {  
			if (p.key.compareTo(key)>0) {       
				x.Increment();
				q = p;         
				p = p.left;         
			} 
			else if (p.key.compareTo(key)<0) {
				x.Increment();
				q = p;    
				p = p.right;
			}

			else { 
				if ((p.left != null) && (p.right != null)) { 
					BSTNode<K, T> min = p.right;
					q = p;
					while (min.left != null) {
						q = min;
						min = min.left;
					}
					p.key = min.key;
					p.data = min.data;
					k1 = min.key;
					p = min;
				}

				if (p.left != null) 
					p = p.left;
				else 
					p = p.right;

				if (q == null) 
					root = p;
				else {
					if (p.key.compareTo(k1)>0) 
						q.left = p;
					else
						q.right = p;
					x.Increment();
				}

				current = root;
				return new Pair<Boolean, Integer>(true, x.get());
			}
		}
		return new Pair<Boolean, Integer>(false, x.get());
	}


	@Override
	public List<K> getAll() {
		List<K> l = new LinkedList<K>();
		inOrder(root, l);
		return l;
	}

	private void inOrder(BSTNode<K, T> e, List<K> l ) {
		if(e == null)
			return;

		inOrder(e.left, l);
		l.insert(e.key);
		inOrder(e.right, l);

	}
}
