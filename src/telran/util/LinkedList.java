package telran.util;

import java.util.Iterator;
import java.util.function.Predicate;

public class LinkedList<T> implements List<T> {
	private static class Node<T> {
		T obj;
		Node<T> next;
		Node<T> prev;

		Node(T obj) {
			this.obj = obj;
		}
	}

	Node<T> head;
	Node<T> tail;
	int size;

	@Override
	public boolean add(T obj) {
		Node<T> node = new Node<>(obj);
		addNode(size, node);
		return true;
	}

	private void addNode(int index, Node<T> node) {

		if (index == size) {
			addTail(node);
		} else if (index == 0) {
			addHead(node);
		} else {
			addMiddle(index, node);
		}
		size++;

	}

	private void addMiddle(int index, Node<T> node) {

		Node<T> nextNode = getNode(index);
		Node<T> prevNode = nextNode.prev;
		node.next = nextNode;
		nextNode.prev = node;
		prevNode.next = node;
		node.prev = prevNode;

	}

	private void addHead(Node<T> node) {
		node.next = head;
		head.prev = node;
		head = node;

	}

	private void addTail(Node<T> node) {
		if (tail == null) {
			head = tail = node;
		} else {
			tail.next = node;
			node.prev = tail;
		}
		tail = node;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			private Node<T> currentNode = head;

			@Override
			public boolean hasNext() {
				return currentNode != null;
			}

			@Override
			public T next() {
				T result = currentNode.obj;
				currentNode = currentNode.next;
				return result;
			}
		};
	}

	@Override
	public void add(int index, T obj) {
		indexValidation(index, true);
		Node<T> node = new Node<>(obj);
		addNode(index, node);

	}

	@Override
	public T get(int index) {
		indexValidation(index, false);
		Node<T> node = getNode(index);
		return node.obj;
	}

	private Node<T> getNode(int index) {

		return index < size / 2 ? getNodeFromHead(index) : getNodeFromTail(index);
	}

	private Node<T> getNodeFromTail(int index) {
		Node<T> current = tail;
		for (int i = size - 1; i > index; i--) {
			current = current.prev;
		}
		return current;
	}

	private Node<T> getNodeFromHead(int index) {
		Node<T> current = head;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		return current;
	}

	@Override
	public T set(int index, T obj) {
	    indexValidation(index, false);
	    Node<T> node = getNode(index);
	    T oldValue = node.obj;
	    node.obj = obj;
	    return oldValue;
	}

	@Override
	public T remove(int index) {
	    indexValidation(index, false);
	    Node<T> node = getNode(index);
	    T removedValue = node.obj;

	    if (node.prev != null) {
	        node.prev.next = node.next;
	    } else {
	        head = node.next;
	    }

	    if (node.next != null) {
	        node.next.prev = node.prev;
	    } else {
	        tail = node.prev;
	    }

	    size--;
	    return removedValue;
	}

	@Override
	public int indexOf(Object pattern) {
	    int index = 0;
	    for (Node<T> node = head; node != null; node = node.next) {
	        if (pattern == null ? node.obj == null : pattern.equals(node.obj)) {
	            return index;
	        }
	        index++;
	    }
	    return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
	    int index = size - 1;
	    for (Node<T> node = tail; node != null; node = node.prev) {
	        if (pattern == null ? node.obj == null : pattern.equals(node.obj)) {
	            return index;
	        }
	        index--;
	    }
	    return -1;
	}

	@Override
	public int indexOf(Predicate<T> predicate) {
	    int index = 0;
	    for (Node<T> node = head; node != null; node = node.next) {
	        if (predicate.test(node.obj)) {
	            return index;
	        }
	        index++;
	    }
	    return -1;
	}

	@Override
	public int lastIndexOf(Predicate<T> predicate) {
	    int index = size - 1;
	    for (Node<T> node = tail; node != null; node = node.prev) {
	        if (predicate.test(node.obj)) {
	            return index;
	        }
	        index--;
	    }
	    return -1;
	}

}
