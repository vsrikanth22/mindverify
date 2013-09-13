package verify.network.hc.axc;

import java.io.InputStream;

public interface ContentHandler<T> {
	
	public T parse(InputStream in) throws Exception;

}
