
public class LinkedList<T> implements List<T> {
	Node<T> head, current;

	@Override
	public boolean empty() {
		return head == null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void findFirst() {
		current = head;
	}

	@Override
	public void findNext() {
		current = current.next;
	}

	@Override
	public boolean last() {
		return current.next == null;
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
	public void insert(T e) {
		Node<T> tmp;
		if (empty()) {
			current = head = new Node<T> (e);
		}
		else {
			tmp = current.next;
			current.next = new Node<T> (e);
			current = current.next;
			current.next = tmp;
		}

	}

	@Override
	public void remove() {
		if (current == head) 
			head = head.next;
		else {
			Node<T> tmp = head;

			while (tmp.next != current)
				tmp = tmp.next;

			tmp.next = current.next;
		}

		if (current.next == null)
			current = head;
		else
			current = current.next;
	}

}
