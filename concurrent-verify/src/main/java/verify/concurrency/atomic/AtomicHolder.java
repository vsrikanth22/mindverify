package verify.concurrency.atomic;

import static java.lang.Double.doubleToRawLongBits;
import static java.lang.Double.longBitsToDouble;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;

/**
 * CAS. compare and set
 * 
 * @author e467885
 * 
 */
public class AtomicHolder {

	private AtomicBoolean atomicBoolean;
	private AtomicInteger atomicInteger;
	private AtomicLongFieldUpdater<AtomicHolder> updater;

	private transient volatile long value;

	public AtomicHolder() {
		atomicBoolean = new AtomicBoolean(false);
		atomicInteger = new AtomicInteger(0);
		updater = AtomicLongFieldUpdater.newUpdater(AtomicHolder.class, "value");
		value = doubleToRawLongBits(0);
	}

	public boolean getBoolean() {
		return atomicBoolean.get();
	}

	public void lazySetIntValue(int value) {
		atomicInteger.lazySet(value);
	}

	public void setIntValue(int value) {
		atomicInteger.set(10);
	}

	public void setTrue() {
		boolean result = atomicBoolean.compareAndSet(false, true);
		System.out.println(Thread.currentThread().getName() + "set value is " + (result ? "success" : "failure"));
	}

	public void lazySetTrue() {
		atomicBoolean.lazySet(true);
		System.out.println("lazy set" + Thread.currentThread().getName() + "current value is " + atomicBoolean.get());
		System.out.println(Thread.currentThread().getName() + "current valus is " + getBoolean());
	}

	public void lazySetFalse() {
		atomicBoolean.lazySet(false);
		System.out.println("lazy set" + Thread.currentThread().getName() + "current value is " + atomicBoolean.get());
		System.out.println(Thread.currentThread().getName() + "current valus is " + getBoolean());
	}

	public void setFalse() {
		boolean result = atomicBoolean.compareAndSet(true, false);
		System.out.println("lazy set" + Thread.currentThread().getName() + "set value is " + (result ? "success" : "failure"));
		System.out.println("current valus is " + getBoolean());
	}

	public double getDouble() {
		return longBitsToDouble(value);
	}

	public void setDouble(double excepted, double update) {
		boolean result = updater.compareAndSet(this, doubleToRawLongBits(excepted), doubleToRawLongBits(update));
		System.out.println(Thread.currentThread().getName() + "set value is " + (result ? "success" : "failure"));
		
	}

}
