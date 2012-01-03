package verify.mvel.demo;

public class User {
	
	

	public User() {
		this("user1");
	}

	public User(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
