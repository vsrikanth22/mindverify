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

	public static Scope value(String scope) {
		if (scope != null) {
			for (Scope b : Scope.values()) {
				if (scope.equalsIgnoreCase(b.scope)) {
					return b;
				}
			}
		}
		throw new IllegalArgumentException("The scope" + scope + "is not existed.");
	}

}
