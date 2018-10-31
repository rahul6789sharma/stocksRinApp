package org.stocksrin.option.common.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.WriteLock;

public class LockObject {

	private LockObject(){
		
	}
	private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
	private static Lock readLock = lock.readLock();
	private static WriteLock writeLock = lock.writeLock();

	public static void getWriteLock() {
		try {
			writeLock.lock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void getReadLock() {
		try {
			readLock.lock();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void realseWriteLock() {
		try {
			writeLock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void realseReadLock() {
		try {
			readLock.unlock();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}