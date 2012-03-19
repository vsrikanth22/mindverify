package verify.asm;

import java.io.Serializable;

public class JavaClass implements Serializable {
	

	private String name = "native";

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
