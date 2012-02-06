package verify.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class GenericIocTest {
	
	public static void main(String[] args) {
		
		Injector injector = Guice.createInjector(new GenericModule());
		
		Runnable runnable = injector.getInstance(Runnable.class);
		
	}

}
