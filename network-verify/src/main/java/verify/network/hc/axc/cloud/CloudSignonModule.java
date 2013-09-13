package verify.network.hc.axc.cloud;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

import verify.network.hc.axc.security.SignonModule;

public class CloudSignonModule implements SignonModule {

	private HttpClient hc;
	private Environment environment;
	private String token;

	public CloudSignonModule(Environment environment, HttpClient hc) {
		this.hc = hc;
	}

	@Override
	public void signon(String username, String password) {
		HttpPost httpPost = new HttpPost(environment.getUrl());
		try {
			HttpResponse response = hc.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (response.getStatusLine().getStatusCode() == 302 && entity != null) {
				EntityUtils.consumeQuietly(entity);
				// TODO token;
			}
		} catch (IOException e) {

		}
	}

	@Override
	public String getToken() {
		return token;
	}

}
