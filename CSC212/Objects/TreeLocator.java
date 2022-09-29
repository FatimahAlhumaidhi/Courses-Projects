public class TreeLocator<T> implements Locator<T> {
	private TreeLocatorNode<T> root, current;

	@Override
	public int add(T e, Location loc) {
		if(loc == null || e == null)
			return 0;
		if(root == null) {
			root = current = new TreeLocatorNode<T>(loc, e);
			return 0;
		}

		IntegerWrapper x = new IntegerWrapper();
		add(root, e, loc, x);
		return x.get();
	}

	private void add(TreeLocatorNode<T> node, T e, Location loc, IntegerWrapper x){
		
		if(node.loc.x == loc.x && node.loc.y == loc.y) {
			node.list.insert(e);
			return;
		}

		x.Increment();

		if( loc.x < node.loc.x && loc.y <= node.loc.y ) {
			if(node.c1 == null) 
				node.c1 = new TreeLocatorNode<T>(loc, e);
			else
				add(node.c1, e, loc, x);
		}
		else if( loc.x <= node.loc.x && loc.y > node.loc.y ) {
			if(node.c2 == null) 
				node.c2 = new TreeLocatorNode<T>(loc, e);
			else
				add(node.c2, e, loc, x);
		}
		else if( loc.x > node.loc.x && loc.y >= node.loc.y ) {
			if(node.c3 == null) 
				node.c3 = new TreeLocatorNode<T>(loc, e);
			else
				add(node.c3 ,e, loc, x);
		}
		else if( loc.x >= node.loc.x && loc.y < node.loc.y ) {
			if(node.c4 == null) 
				node.c4 = new TreeLocatorNode<T>(loc, e);
			else
				add(node.c4, e, loc,x );
		}
	}

	@Override
	public Pair<List<T>, Integer> get(Location loc) {
		IntegerWrapper x = new IntegerWrapper();

		if(exists(loc, root, x)) 
			return new Pair<List<T>, Integer>(current.list, x.get());

		return new Pair<List<T>, Integer>(new LinkedList<T>(), x.get());
	}

	private boolean exists(Location loc, TreeLocatorNode<T> node, IntegerWrapper x) {
		if(node == null) 
			return false;

		x.Increment();

		if(node.loc.x == loc.x && node.loc.y == loc.y) {
			current = node;
			return true;
		}

		else if( loc.x < node.loc.x && loc.y <= node.loc.y ) 
			return exists(loc, node.c1, x);

		else if( loc.x <= node.loc.x && loc.y > node.loc.y )
			return exists(loc, node.c2, x);

		else if( loc.x > node.loc.x && loc.y >= node.loc.y )
			return exists(loc, node.c3, x);

		else if( loc.x >= node.loc.x && loc.y < node.loc.y )
			return exists(loc, node.c4, x);

		return false;
	}

	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
		IntegerWrapper x = new IntegerWrapper();
		boolean flag = false;

		if(e == null || loc == null)
			return new Pair<Boolean, Integer>(flag, x.get());

		if(exists(loc, root, x) && current.list != null && !current.list.empty()) {
			current.list.findFirst();
			while(!current.list.last()) {
				if((current.list.retrieve()).equals(e)) {
					current.list.remove();
					flag = true;
					continue;
				}
				current.list.findNext();
			}
			if((current.list.retrieve()).equals(e)) {
				current.list.remove();
				flag = true;
			}
		}
		return new Pair<Boolean, Integer>(flag, x.get());
	}

	@Override
	public List<Pair<Location, List<T>>> getAll() {
		List<Pair<Location, List<T>>> l = new LinkedList<Pair<Location, List<T>>>();
		all(root, l);
		return l;
	}

	private void all(TreeLocatorNode<T> e, List<Pair<Location, List<T>>> l) {
		if(e == null)
			return;

		l.insert(new Pair<Location, List<T>>(e.loc, e.list));
		all(e.c1, l);
		all(e.c2, l);
		all(e.c3, l);
		all(e.c4, l);
	}


	@Override
	public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
		List<Pair<Location, List<T>>> l = new LinkedList<Pair<Location, List<T>>>();
		IntegerWrapper x = new IntegerWrapper();

		if(lowerLeft != null && upperRight != null && lowerLeft.x <= upperRight.x && lowerLeft.y <= upperRight.y)
			inRange(lowerLeft, upperRight, root, l, x);

		return new Pair<List<Pair<Location, List<T>>>, Integer>(l, x.get());
	}

	private void inRange(Location lowerLeft, Location upperRight, TreeLocatorNode<T> e, List<Pair<Location, List<T>>> l, IntegerWrapper x){
		if(e == null)
			return;

		x.Increment();

		if(e.loc.x < lowerLeft.x) {
			if(e.loc.y <= upperRight.y)
				inRange(lowerLeft, upperRight, e.c3, l, x);
			if(e.loc.y > lowerLeft.y)
				inRange(lowerLeft, upperRight, e.c4, l, x);
		}

		else if(e.loc.x >= lowerLeft.x && e.loc.x <= upperRight.x) { 	
			if(e.loc.y >= lowerLeft.y && e.loc.y <= upperRight.y)
				l.insert(new Pair<Location, List<T>>(e.loc, e.list));

			if(e.loc.x != lowerLeft.x && e.loc.y >= lowerLeft.y)
				inRange(lowerLeft, upperRight, e.c1, l, x);
			if(e.loc.y < upperRight.y)
				inRange(lowerLeft, upperRight, e.c2, l, x);
			if(e.loc.x != upperRight.x && e.loc.y <= upperRight.y)
				inRange(lowerLeft, upperRight, e.c3, l, x);
			if(e.loc.y > lowerLeft.y)
				inRange(lowerLeft, upperRight, e.c4, l, x);
		}

		else if(e.loc.x > upperRight.x) {
			if(e.loc.y >= lowerLeft.y)
				inRange(lowerLeft, upperRight, e.c1, l, x);
			if(e.loc.y < upperRight.y)
				inRange(lowerLeft, upperRight, e.c2, l, x);
		}
	}

}
/*	private void inRange(Location lowerLeft, Location upperRight, TreeLocatorNode<T> e, List<Pair<Location, List<T>>> l, IntegerWrapper x){
		if(e == null)
			return;

		x.Increment();

		if(e.loc.x < lowerLeft.x) { // then check c3 and c4 
			if(!(e.loc.y > upperRight.y))
				inRange(lowerLeft, upperRight, e.c3, l, x);
			if(!(e.loc.y <= lowerLeft.y))
				inRange(lowerLeft, upperRight, e.c4, l, x);
		}

		else if(e.loc.x >= lowerLeft.x && e.loc.x <= upperRight.x ) { 
			if(e.loc.y < lowerLeft.y){ // then check c2 and c3
				inRange(lowerLeft, upperRight, e.c2, l, x);
				if(e.loc.x != upperRight.x)
					inRange(lowerLeft, upperRight, e.c3, l, x);
			}

			else if(e.loc.y >= lowerLeft.y && e.loc.y <= upperRight.y ) { // then check all c1 c2 c3 c4
				l.insert(new Pair<Location, List<T>>(e.loc, e.list));
				if(e.loc.x != lowerLeft.x)
					inRange(lowerLeft, upperRight, e.c1, l, x);
				if(e.loc.y != upperRight.y)
					inRange(lowerLeft, upperRight, e.c2, l, x);
				if(e.loc.x != upperRight.x)
					inRange(lowerLeft, upperRight, e.c3, l, x);
				if(e.loc.y != lowerLeft.y)
					inRange(lowerLeft, upperRight, e.c4, l, x);
			}

			else if(e.loc.y > upperRight.y) { // then check c1 and c4
				if(e.loc.x != lowerLeft.x)
					inRange(lowerLeft, upperRight, e.c1, l, x);
				inRange(lowerLeft, upperRight, e.c4, l, x);
			}
		}

		else if(e.loc.x > upperRight.x ) { // then check c1 c2
			if(!(e.loc.y < lowerLeft.y))
				inRange(lowerLeft, upperRight, e.c1, l, x);
			if(!(e.loc.y >= upperRight.y))
				inRange(lowerLeft, upperRight, e.c2, l, x);
		}
	}*/

