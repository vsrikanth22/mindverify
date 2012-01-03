package verify.ioc;

import java.util.Map;

import com.google.inject.Singleton;
import com.google.inject.binder.ScopedBindingBuilder;
import com.google.inject.name.Names;

public class ComponentBuilder extends AbstractPrivateModuleBuilder<Component> {

	private String name;
	private Class<? extends Component> implementedClazz;
	private boolean singleton = false;
	private Map<String, String> properties = null;

	public ComponentBuilder(String name, Class<? extends Component> implementedClazz) {
		super();
		this.name = name;
		this.implementedClazz = implementedClazz;
	}

	@Override
	protected void configure() {
		ScopedBindingBuilder builder = bind(interfaceCls).annotatedWith(Names.named(name)).to(implementedClazz);

		if (singleton) {
			builder.in(Singleton.class);
		}

		if (properties != null && properties.size() > 0) {
			Names.bindProperties(binder(), properties);
		}

		// expose the component;
		expose(interfaceCls).annotatedWith(Names.named(name));
	}

	public ComponentBuilder singleton() {
		this.singleton = true;
		return this;
	}

	public ComponentBuilder properties(Map<String, String> properties) {
		this.properties = properties;
		return this;
	}

}
