package verify.network.hc.axc.cloud;

import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.client.HttpClient;

import verify.network.hc.axc.AxcReqExecutor;
import verify.network.hc.axc.ConcurrentAxcReqExecutor;
import verify.network.hc.axc.ContentHandler;
import verify.network.hc.axc.security.SignonModule;
import static verify.network.hc.axc.utils.AxcHelper.*;

public class CloudReqExecutor implements AxcReqExecutor {

	private ConcurrentAxcReqExecutor reqExecutor;

	public CloudReqExecutor(String username, String password) {
		HttpClient hc = defaultHttpClient();
		SignonModule module = new CloudSignonModule(Environment.PROD, hc);
		module.signon(username, password);

		this.reqExecutor = new ConcurrentAxcReqExecutor(defaultHttpClient(), Executors.newCachedThreadPool(), module);
	}

	@Override
	public <T> Future<T> call(String url, Map<String, String> params, ContentHandler<T> handler) {
		return reqExecutor.call(url, params, handler);
	}

}
