package verify.concurrency.atomic;

public class AtomicDouble extends Number {


	private static final long serialVersionUID = -2905958301426558713L;
	private static volatile long value;

	@Override
	public int intValue() {
		return 0;
	}

	@Override
	public long longValue() {
		return 0;
	}

	@Override
	public float floatValue() {
		return 0;
	}

	@Override
	public double doubleValue() {
		return 0;
	}

}
