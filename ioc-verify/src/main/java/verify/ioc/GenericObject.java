package verify.ioc;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class GenericObject<T> implements Runnable {
	
	Provider<GenericProvider<T>> provider = null;
	
	@Inject
	public GenericObject(Provider<GenericProvider<T>> provider) {
		this.provider = provider;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
