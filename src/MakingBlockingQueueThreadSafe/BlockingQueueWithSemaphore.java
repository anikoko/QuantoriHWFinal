package MakingBlockingQueueThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BlockingQueueWithSemaphore<T> {

	public static final int PERMITE_SIZE = 10;

	List<T> items = new ArrayList<>();

	Semaphore sem = new Semaphore(PERMITE_SIZE);
	Semaphore mutexSem = new Semaphore(1);
	Semaphore isEmpty = new Semaphore(0);

	public void put(T item) {
		sem.acquireUninterruptibly();
		try {
			mutexSem.acquire();
			items.add(item);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutexSem.release();
		}

		isEmpty.release();

	}

	public T get() {
		T item = null;
		isEmpty.acquireUninterruptibly();
		try {
			mutexSem.acquire();
			item = items.remove(0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			mutexSem.release();
		}
		sem.release();
		return item;

	}
}
