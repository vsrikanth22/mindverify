package verify.ioc;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;

public class GenericModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(new TypeLiteral<GenericProvider<Object>>(){});
		bind(Runnable.class).to(new TypeLiteral<GenericObject<Object>>(){});
	}

}
