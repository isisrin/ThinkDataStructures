/**
 * 
 */
package structures;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * @author downey
 * @param <E>
 *
 */
public class MyLinkedList<E> implements List<E> {

	/**
	 * Node is identical to ListNode from the example, but parameterized with T
	 *
	 * @author downey
	 *
	 */
	private class Node {
		public E data;
		public Node next;

		public Node(E data) {
			this.data = data;
			this.next = null;
		}
		@SuppressWarnings("unused")
		public Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}
		public String toString() {
			return "Node(" + data.toString() + ")";
		}
	}

	private int size;            // keeps track of the number of elements
	private Node head;           // reference to the first node

	/**
	 *
	 */
	public MyLinkedList() {
		head = null;
		size = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// run a few simple tests
		List<Integer> mll = new MyLinkedList<Integer>();
		mll.add(1);
		mll.add(2);
		mll.add(3);
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());

		mll.remove(new Integer(2));
		System.out.println(Arrays.toString(mll.toArray()) + " size = " + mll.size());
	}

	@Override
	public boolean add(E element) {
		if (head == null) {
			head = new Node(element);
		} else {
			Node node = head;
			// loop until the last node
			for ( ; node.next != null; node = node.next) {}
			node.next = new Node(element);
		}
		size++;
		return true;
	}

	@Override
	public void add(int index, E element) {
		if (index == 0) {
			head = new Node(element, head);
		} else {
			Node node = getNode(--index);
			node.next = new Node(element, node.next);
		}
		size++;
	}

	@Override
	public boolean addAll(Collection<? extends E> collection) {
		boolean flag = true;
		for (E element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object obj: collection) {
			if (!contains(obj)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public E get(int index) {
		Node node = getNode(index);
		return node.data;
	}

	/** Returns the node at the given index.
	 * @param index
	 * @return
	 */
	private Node getNode(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		Node node = head;
		for (int i=0; i<index; i++) {
			node = node.next;
		}
		return node;
	}

	@Override
	public int indexOf(Object target) {
		for (int index = 0; index < size(); index++) {
			if(equals(target, get(index))) {
				return index;
			}
		}
		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<E> iterator() {
		E[] array = (E[]) toArray();
		return Arrays.asList(array).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		Node node = head;
		int index = -1;
		for (int i=0; i<size; i++) {
			if (equals(target, node.data)) {
				index = i;
			}
			node = node.next;
		}
		return index;
	}

	@Override
	public ListIterator<E> listIterator() {
		return null;
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		return null;
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public E remove(int index) {
		Node node = getNode(index);
		if(index == 0) {
			head = node.next;
		} else {
			// index의 값이 참조되기 전 증가
			// index++ 의 경우는 참조한 다음에 증가함 (다음 줄에서 값이 +1되는 것이라 생각하면 될 듯)
			Node preNode = getNode(--index);
			preNode.next = node.next;
		}
		size--;
		return node.data;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		for (Object obj: collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	public E set(int index, E element) {
		Node node = getNode(index);
		E old = node.data;
		node.data = element;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		// TODO: classify this and improve it.
		int i = 0;
		MyLinkedList<E> list = new MyLinkedList<E>();
		for (Node node=head; node != null; node = node.next) {
			if (i >= fromIndex && i <= toIndex) {
				list.add(node.data);
			}
			i++;
		}
		return list;
	}

	@Override
	public Object[] toArray() {
		Object[] array = new Object[size];
		int i = 0;
		for (Node node=head; node != null; node = node.next) {
			// System.out.println(node);
			array[i] = node.data;
			i++;
		}
		return array;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
	}
}
