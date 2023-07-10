package telran.util.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.*;

abstract class ListTest extends CollectionTest {

	List<Integer> list;
	@BeforeEach
	@Override
	void setUp() {
		super.setUp();
		list = (List<Integer>)collection;
	}

	

	@Override
	void addTest() {
		Integer [] expected = {10,  -20, 8, 14, 30, 12, 100, 10};
		assertTrue(list.add(10));
		assertArrayEquals(expected, list.toArray(new Integer[0]));
		
	}
	@Test
	void addIndexTest() {
		Integer [] expected1 = {10, 10, -20, 8, 14, 30, 12, 100};
		Integer [] expected2 = {10, 10, -20, 8, 14, -20, 30, 12, 100};
		Integer [] expected3 = {10, 10, -20, 8, 14, -20, 30, 12, 100, 10};
		list.add(0, 10);
		assertArrayEquals(expected1, list.toArray(new Integer[0]));
		list.add(5, -20);
		assertArrayEquals(expected2, list.toArray(new Integer[0]));
		list.add(9, 10);
		assertArrayEquals(expected3, list.toArray(new Integer[0]));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(11, 10));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.add(-1, 10));
	}
	@Test
	void getIndexTest() {
		for (int i = 0; i < numbers.length; i++) {
			assertEquals(numbers[i], list.get(i));
		}
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(list.size()));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.get(-1));
	}
	@Test
	void indexOfTest() {
		assertEquals(4, list.indexOf(n -> n >= 30));
		assertEquals(6, list.lastIndexOf(n -> n >= 30));
		assertEquals(-1, list.indexOf(n -> n % 2 != 0));
		assertEquals(-1, list.lastIndexOf(n -> n % 2 != 0));
		list.add(5, 10);
		assertEquals(0, list.indexOf(10));
		assertEquals(5, list.lastIndexOf(10));
		
	}
	@Test
	void removeIndex() {
		Integer [] expected1 = { -20, 8, 14, 30, 12, 100};
		Integer [] expected2 = { -20, 8, 30, 12, 100};
		Integer [] expected3 = { -20, 8,  30, 12};
		list.remove(0);
		assertArrayEquals(expected1, list.toArray(new Integer[0]));
		list.remove(2);
		assertArrayEquals(expected2, list.toArray(new Integer[0]));
		list.remove(4);
		assertArrayEquals(expected3, list.toArray(new Integer[0]));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(4));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.remove(-1));
	}
	@Test
	void setTest() {
		Integer [] expected1 = { 10, -20, 7, 14, 30, 12, 100};
		list.set(2, 7);
		assertArrayEquals(expected1, list.toArray(new Integer[0]));
		assertEquals(10, list.get(0));
		list.set(0, 33);
		assertEquals(33, list.get(0));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(list.size(), 3));
		assertThrowsExactly(IndexOutOfBoundsException.class, () -> list.set(-1, 3));
	}
	

	

}
