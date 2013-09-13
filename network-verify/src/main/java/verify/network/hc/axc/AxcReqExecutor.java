package verify.network.hc.axc;

import java.util.Map;
import java.util.concurrent.Future;

public interface AxcReqExecutor {
	
	public <T> Future<T> call(String url, Map<String, String> params, ContentHandler<T> handler);

}
