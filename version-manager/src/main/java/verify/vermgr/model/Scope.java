package verify.vermgr.model;

public enum Scope {

	DEV("dev"), SYS("sys"), UAT("uat"), BUAT("buat"), PROD("prod");

	private String scope;

	Scope(String scope) {
		this.scope = scope;
	}

	public String getScope() {
		return scope;
	}

}
