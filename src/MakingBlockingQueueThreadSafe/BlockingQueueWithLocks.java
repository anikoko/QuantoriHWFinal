package MakingBlockingQueueThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BlockingQueueWithLocks<T> {

	public static final int PERMITE_SIZE = 10;

	List<T> items = new ArrayList<>();

	ReentrantLock lock = new ReentrantLock();
	private final Condition notFull = lock.newCondition();
	private final Condition notEmpty = lock.newCondition();

	public void put(T item) {
		lock.lock();
		try {
			if (items.size() == PERMITE_SIZE) {
				try {
					notFull.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			items.add(item);
			notEmpty.signal();
		} finally {
			lock.unlock();
		}

	}

	public T get() {
		lock.lock();
		try {
			while (items.isEmpty()) {
				try {
					notEmpty.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			T item = items.remove(0);
			notFull.signal();
			return item;
		} finally {
			lock.unlock();
		}

	}
}
