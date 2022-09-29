public class TreeLocatorNode<T> {
	public Location loc;
	public List<T> list;
	public TreeLocatorNode<T> c1, c2, c3, c4;
	
	
	public TreeLocatorNode(Location loc, T e){
		this.loc = loc;
		list = new LinkedList<T>();
		this.list.insert(e);
		c1 = c2 = c3 = c4 = null;
	}
	
}
