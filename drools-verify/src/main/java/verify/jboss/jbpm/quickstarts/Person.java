package verify.jboss.jbpm.quickstarts;

public class Person {

	private int count;
	private String name;

	public Person(String name) {
		setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
