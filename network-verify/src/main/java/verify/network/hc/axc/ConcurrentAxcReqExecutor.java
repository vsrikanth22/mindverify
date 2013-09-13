package verify.network.hc.axc;

import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;

import static verify.network.hc.axc.utils.AxcHelper.*;

import verify.network.hc.axc.security.SignonModule;

/**
 * Concurrent Xdata Client Request Executor
 * 
 * @author shenkai
 * 
 */
public class ConcurrentAxcReqExecutor implements AxcReqExecutor, SignonAdapter {

	final private HttpClient hc;
	private ExecutorService reqExecutor;
	private SignonModule signonModule;

	public ConcurrentAxcReqExecutor(HttpClient hc, ExecutorService reqExecutor, SignonModule signonAdapter) {
		this.hc = hc;
		this.reqExecutor = reqExecutor;
		this.signonModule = signonAdapter;
	}

	@Override
	public void signon(String username, String password) {
		this.signonModule.signon(username, password);
	}

	@Override
	public <T> Future<T> call(String url, Map<String, String> params, ContentHandler<T> handler) {

		return reqExecutor.submit(new ReqTask<T>(url, params, handler));
	}

	private class ReqTask<V> implements Callable<V> {

		private HttpPost httpPost;
		private ContentHandler<V> handler;

		public ReqTask(String url, Map<String, String> params, ContentHandler<V> handler) {
			this.httpPost = new HttpPost(url);
			httpPost.setEntity(createEntity(params));
			this.handler = handler;
		}

		private UrlEncodedFormEntity createEntity(Map<String, String> params) {
			try {
				return new UrlEncodedFormEntity(convertMapToNvp(params), "utf-8");
			} catch (Exception e) {
				throw new AxcException("can not create url encoded form entity.", e);
			}

		}

		@Override
		public V call() throws Exception {
			V v = null;

			HttpResponse response = hc.execute(httpPost);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				try (InputStream in = entity.getContent()) {
					v = handler.parse(in);
				}
			}

			return v;
		}
	}

}
