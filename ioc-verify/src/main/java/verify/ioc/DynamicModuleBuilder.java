package verify.ioc;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.PrivateModule;
import com.google.inject.name.Names;

public class DynamicModuleBuilder implements Module {

	private Map<Class<?>, Class<?>> linking;

	public void setLinking(Map<Class<?>, Class<?>> linking) {
		this.linking = linking;
	}

	@Override
	public void configure(Binder binder) {
		for(Entry<Class<?>, Class<?>> entry : linking.entrySet()) {
			binder.bind(Object.class).to(Object.class);
			to(Object.class, 1L);
		}
	}
	
	public void to(Class<?> clazz, Object value) {
		if(value instanceof Object) {
			System.out.println(value.getClass());
		}
	};
	
	public static void main(String[] args) {
		DynamicModuleBuilder builder = new DynamicModuleBuilder();
		builder.to(Object.class, 1d);
		
		PrivateModule module = new PrivateModule() {
			@Override
			protected void configure() {
				Names.bindProperties(binder(), new Properties());
				expose(Object.class).annotatedWith(Names.named("uniqueId"));
			}
		};
	}

}
