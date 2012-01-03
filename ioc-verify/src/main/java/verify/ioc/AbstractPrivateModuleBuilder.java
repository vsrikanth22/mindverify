package verify.ioc;

import java.lang.reflect.ParameterizedType;

import com.google.inject.PrivateModule;

public abstract class AbstractPrivateModuleBuilder<T> extends PrivateModule {

	protected Class<T> interfaceCls;

	@SuppressWarnings("unchecked")
	public AbstractPrivateModuleBuilder() {
		interfaceCls = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
