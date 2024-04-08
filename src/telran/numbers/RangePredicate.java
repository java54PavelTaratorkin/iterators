package telran.numbers;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class RangePredicate extends Range {
	private Predicate<Integer> predicate = number -> true;
	protected RangePredicate(int min, int max) {
		super(min, max);
	}
	
	public void setPredicate(Predicate<Integer> predicate) {
		this.predicate = predicate;
	}
	
	public static RangePredicate getRange(int min, int max) {
		checkMinMax(min, max);
		return new RangePredicate(min, max);
	}
		
	@Override
	public Iterator<Integer> iterator(){
		return new RangePredicateIterator();
	}
	
	private class RangePredicateIterator implements Iterator<Integer> {
		int current = min;
		
		@Override
		public boolean hasNext() {
			boolean res = false;
			int value = current;
			while(value <= max && !(res = predicate.test(value++)));
			return current < max;
//			return res;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {				
				throw new NoSuchElementException();
			}

////			if (predicate != null) {
//				if (noSuchPredicator(current)) {
//					throw new NoSuchElementException();
//				}
//				nextEvenOdd();
////			} 
			while(!predicate.test(current++));
			
			return current - 1;
		}
		
		private void nextEvenOdd() {
			while (!predicate.test(current)) {
				current++;
			}
		}
		
		private boolean noSuchPredicator(int number) {
			boolean flag = true;
			while (number <= max) {
				if (predicate.test(number)) {
					flag = false;
				}
				number++;
			}
			return flag;
		}
	}	
}
