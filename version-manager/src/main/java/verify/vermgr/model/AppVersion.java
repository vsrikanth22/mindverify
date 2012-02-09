package verify.vermgr.model;

public class AppVersion {

	private String appCode;

	// private Scope scope;

	private String majorVersion;

	private int minorVersion = 0;

	public AppVersion() {}

	public AppVersion(String appCode, String majorVersion) {
		this.appCode = appCode;
		this.majorVersion = majorVersion;
	}

	public String getAppCode() {
		return appCode;
	}

	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}

	public String getMajorVersion() {
		return majorVersion;
	}

	public void setMajorVersion(String majorVersion) {
		this.majorVersion = majorVersion;
	}

	public int getMinorVersion() {
		return minorVersion;
	}

	public void setMinorVersion(int minorVersion) {
		this.minorVersion = minorVersion;
	}

	public AppVersion increment() {
		this.minorVersion += 1;
		return this;
	}

}
