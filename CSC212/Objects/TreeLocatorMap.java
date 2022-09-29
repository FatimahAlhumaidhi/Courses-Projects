public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
	Map<K, Location> map;
	Locator<K> locator;

	TreeLocatorMap(){
		map = new BST<K, Location>();
		locator = new TreeLocator<K>();
	}
	

	@Override
	public Map<K, Location> getMap() {
		return map;
	}

	@Override
	public Locator<K> getLocator() {
		return locator;
	}

	@Override
	public Pair<Boolean, Integer> add(K k, Location loc) {
		if(k == null || loc == null)
			return new Pair<Boolean, Integer>(false, 0);
		
		Pair<Boolean, Integer> p = map.insert(k, loc);
		if(p.first) 
			locator.add(k, loc);

		return p;
	}

	@Override
	public Pair<Boolean, Integer> move(K k, Location loc) {
		
		if(k == null || loc == null)
			return new Pair<Boolean, Integer>(false, 0);
		
		Pair<Boolean, Integer> p = map.find(k);

		if(p.first) {
			locator.remove(k, map.retrieve());
			locator.add(k, loc);
			map.update(loc);
		}

		return p;
	}

	@Override
	public Pair<Location, Integer> getLoc(K k) {
		
		if(k == null)
			return new Pair<Location, Integer>(null, 0);
		
		Pair<Boolean, Integer> p = map.find(k);

		if(p.first) 
			return new Pair<Location, Integer>(map.retrieve(), p.second);

		return new Pair<Location, Integer>(null, p.second);
	}

	@Override
	public Pair<Boolean, Integer> remove(K k) {
		
		if(k == null)
			return new Pair<Boolean, Integer>(false, 0);
		
		Pair<Location, Integer> p = getLoc(k);

		if(p.first != null) {
			map.remove(k);
			locator.remove(k, p.first);
		}
		return new Pair<Boolean, Integer>(p.first != null, p.second);
	}

	@Override
	public List<K> getAll() {
		return map.getAll();
	}

	@Override
	public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) { 
		
		Pair<List<Pair<Location, List<K>>>, Integer> pair = locator.inRange(lowerLeft, upperRight);
		List<Pair<Location, List<K>>> l = pair.first;
		
		List<K> list = new LinkedList<K>();
		if(l != null && !l.empty()) {
			Pair<Location, List<K>> p;
			l.findFirst(); //pairs
			while(!l.last()) { 
				p = l.retrieve();
				if(p.second != null && !p.second.empty()) {
					p.second.findFirst(); //lists
					while(!p.second.last()) {
						list.insert(p.second.retrieve()); //Keys
						p.second.findNext();
					}
					list.insert(p.second.retrieve());
				}
				l.findNext();
			}
			p = l.retrieve();
			if(p.second != null && !p.second.empty()) {
				p.second.findFirst();
				while(!p.second.last()) {
					list.insert(p.second.retrieve());
					p.second.findNext();
				}
				list.insert(p.second.retrieve());
			}
		}
		return new Pair<List<K>, Integer>(list, pair.second);
	}

}
