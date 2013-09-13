package verify.network.hc.axc.cloud;

public enum Environment {

	DEV("https://cloud-dev"), SYS("https://cloud-sys"), UAT("https://cloud-ua"), PROD("https://cloud");

	private String url;

	private Environment(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
