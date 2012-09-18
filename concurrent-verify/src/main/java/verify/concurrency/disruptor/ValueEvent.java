package verify.concurrency.disruptor;

public class ValueEvent {

	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ValueEvent [id=" + id + "]";
	}

}
