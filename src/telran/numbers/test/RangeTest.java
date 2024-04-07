package telran.numbers.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

import telran.numbers.Range;

class RangeTest {
	Range range = Range.getRange(5, 10);
	
	@Test
	void iterableIteratorTest() {
		Integer[] expected = {5, 6, 7, 8, 9, 10};
		
		Integer[] actual = new Integer[expected.length];
//		System.out.println(Arrays.toString(toArrayFromIterable(new Integer[expected.length], range)));
		assertArrayEquals(expected, toArrayFromIterable(actual, range));
	}
	
	@Test
	void iteratorIncorrectUsageTest() {
		Iterator<Integer> it = range.iterator();
		while (it.hasNext()) {
			it.next();
		}
		assertThrowsExactly(NoSuchElementException.class, () -> it.next());
	}
	
	protected <T> T[] toArrayFromIterable(T[] array, Iterable<T> iterable) {
		int index = 0;
//		System.out.println(index);
		
		for(T obj: iterable) {			
			array[index++] = obj;
//			System.out.println(index);
//			System.out.println(Arrays.toString(array));
			
		}
		// This is same code as above
//		Iterator<T> it = iterable.iterator();
//		while (it.hasNext()) {
//			array[index++] = it.next();
//		}
		return array;
	}
}
