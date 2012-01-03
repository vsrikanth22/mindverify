package verify.ioc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;

public class ComponentBuilderTest {

	public static void main(String[] args) {

		ComponentBuilder builder = new ComponentBuilder("unique", SimpleComponent.class).singleton();

		Injector injector = Guice.createInjector(builder);
		Component component = injector.getInstance(Key.get(Component.class, Names.named("unique")));
		System.out.println(component.toString());

	}

}
