/**
 * @author clack008@gmail.com
 */

package week1;

import java.util.Collection;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericStack<E> extends Assert implements Stack<E> {

	public static final int DEFAULT_CAPACITY = 20;
	private int N;
	private int capacity;
	private Node first;
	private static final Logger log = LoggerFactory
			.getLogger(GenericStack.class);

	private class Node {
		private E element;
		private Node next;
	}

	public GenericStack() {
		log.info("������ ����������� �� ���������.");
		this.capacity = DEFAULT_CAPACITY;
		N = 0;
	}

	public GenericStack(int capacity) {
		log.info("������ ����������� � ����������.");
		this.capacity = capacity;
		N = 0;
	}

	@Override
	public void push(E element) throws StackException {
		if (isFull()) {
			log.error("���� ����������.");
			throw new StackException("Stack overflow");
		}
		Node oldfirst = first;
		first = new Node();
		first.element = element;
		first.next = oldfirst;
		N++;
		log.info("������� " + element + " ������� ������� � ����.");
	}

	@Override
	public E pop() throws StackException {
		if (isEmpty()) {
			log.error("���� ����.");
			throw new StackException("Stack underflow");
		}

		assertTrue(N > 0); // JUnit ����� �� ����� ��������

		E element = first.element;
		first = first.next;
		N--;
		log.info("������� " + element + " ������� �������� �� �����.");
		return element;
	}

	@Override
	public E peek() {
		log.info("�������� ��������� �������.");
		return first.element;
	}

	@Override
	public int getSize() {
		log.info("��� �������� ������ �����.");
		return N;
	}

	@Override
	public boolean isEmpty() {
		log.info("���� ��� �������� �� �������.");
		return N == 0;
	}

	@Override
	public boolean isFull() {
		log.info("���� ��� �������� �� �������.");
		return N == capacity;
	}

	@Override
	public void pushAll(Collection<? extends E> src) throws StackException {
		if (src.size() > capacity - N) {
			log.error("� ��������������� ��������� ������ ������ ���������, ��� ����� �������� ����.");
			throw new StackException(
					"Size of the source is greater than stack can handle");
		}
		for (E element : src) {
			push(element);
		}
		log.info("��� �������� ������� �������������.");
	}

	@Override
	public void popAll(Collection<? super E> dst) throws StackException {
		if (dst.size() < N) {
			log.error("������ �������� ��������� ������ ������, ��� ���������� ���������, ������� ����� ������������ ����.");
			throw new StackException(
					"Size of the destination is less than quantity of elements in stack");
		}
		while (!isEmpty()) {
			dst.add(pop());
		}
		log.error("��� �������� ������� ��������������.");
	}

	public static void main(String[] args) throws StackException {
		GenericStack<String> str = new GenericStack<>();
		str.push("elem1");
		str.pop();
		str.pop();
	}

}
