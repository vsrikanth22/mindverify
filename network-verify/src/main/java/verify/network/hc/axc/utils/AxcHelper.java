package verify.network.hc.axc.utils;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;

import verify.network.hc.axc.AxcException;

public abstract class AxcHelper {

	@SuppressWarnings("deprecation")
	public static HttpClient defaultHttpClient() {

		SchemeRegistry registry = new SchemeRegistry();
		Scheme http = new Scheme("http", 80, PlainSocketFactory.getSocketFactory());

		SSLSocketFactory sf = new SSLSocketFactory(defaultSslCtx(), SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		Scheme https = new Scheme("https", 443, sf);

		registry.register(http);
		registry.register(https);

		ClientConnectionManager cm = new PoolingClientConnectionManager(registry);
		HttpClient hc = new DefaultHttpClient(cm);

		hc.getParams().setBooleanParameter(ClientPNames.HANDLE_REDIRECTS, false);
		hc.getParams().setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);

		return hc;
	}

	public static SSLContext defaultSslCtx() {

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				}
			} }, null);
			return ctx;
		} catch (KeyManagementException | NoSuchAlgorithmException e) {
			throw new AxcException("can not create SSL Context for Alternative Xdata Client.", e);
		}
	}
	
	public static List<NameValuePair> convertMapToNvp(Map<String, String> params) {
		if (params != null && params.size() > 0) {
			List<NameValuePair> _reqParams = new ArrayList<>(params.size());
			for (Entry<String, String> entry : params.entrySet()) {
				_reqParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			return _reqParams;
		}
		return Collections.emptyList();
	}


}
