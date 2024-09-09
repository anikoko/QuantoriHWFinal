package MakingBlockingQueueThreadSafe;

import java.util.ArrayList;
import java.util.List;

public class BlockingQueueThreadSafe<T> {

	List<T> items = new ArrayList<>();

	Object mutex = new Object();
	
	public void put(T item) {
		synchronized (mutex) {
			items.add(item);
			mutex.notify();
		}
	}

	
	public T get() {
		synchronized (mutex) {
			while (items.isEmpty()) {
				synchronized (mutex) {
					try {
						mutex.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			return items.remove(0);
		}
	}
}
